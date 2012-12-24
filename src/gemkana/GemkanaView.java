/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gemkana;

import environment.Environment;
import environment.Grid;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author kevin.lawrence
 */
class GemkanaView extends Environment {

    private Dimension cellSize;
    private Dimension gemSize;
    private Point fieldPosition;
    private GemField gemField;
    private Grid grid;
    private GemTypeView gemTypeView;
    private boolean advanced;
    private boolean debug;

    public GemkanaView(Image background) {
        super(background);
    }

    @Override
    public void initializeEnvironment() {
        cellSize = new Dimension(60, 60);
        gemSize = new Dimension(50, 50);
        fieldPosition = new Point(190, 20);

        gemField = new GemField(8, 8);
        grid = new Grid(gemField.getSize().width, gemField.getSize().height, cellSize.width, cellSize.height, fieldPosition, Color.WHITE);

        gemTypeView = new GemTypeView();
    }

    @Override
    public void timerTaskHandler() {
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            this.gemField.randomize();
        } else if (e.getKeyCode() == KeyEvent.VK_C) {
//            scoreSequence();
            collapseSequence();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            advanced = !advanced;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            debug = !debug;
        }
    }

    private void collapseSequence() {
        ArrayList<Point> gs = gemField.getGemSequence();
        System.out.println("Sequence = " + gs.toString());
        this.gemField.removeSequence(gs);
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        Point location = this.grid.cellPointCalculator(e.getX(), e.getY());

        if ((location.x <= gemField.getColumns()) && (location.y <= gemField.getRows())) {
            if (gemField.updateSelected(location)) {
                if (gemField.getSelectedCount() == 2) {
                    if (gemField.tryGemLocationSwitch()) {
                        gemField.clearSelected();
                    } else {
                        //back out the switch
                        java.awt.Toolkit.getDefaultToolkit().beep();
                    }
                }                
            } else {
                java.awt.Toolkit.getDefaultToolkit().beep();
            }
        }
//        System.out.printf("Grid(%d, %d)\n", gridCoordinate.x, gridCoordinate.y);
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        for (int row = 0; row < this.gemField.getRows(); row++) {
            for (int column = 0; column < this.gemField.getColumns(); column++) {

                if (advanced) {
                    drawAdvancedGem(graphics, this.gemField.getGems()[column][row],
                            new Point(fieldPosition.x + (column * cellSize.width), fieldPosition.y + (row * cellSize.height)),
                            this.cellSize, this.gemSize,
                            gemField.isSelected(new Point(column, row)));
                } else {
                    drawBaseGem(graphics, this.gemField.getGems()[column][row],
                            new Point(fieldPosition.x + (column * cellSize.width), fieldPosition.y + (row * cellSize.height)),
                            this.cellSize, this.gemSize,
                            gemField.isSelected(new Point(column, row)));
                }
            }
        }
        
        if (debug) {
            markGemSequences(graphics);
        }

        if (this.grid != null) {
            this.grid.paintComponent(graphics);
        }
    }

    public void drawBaseGem(Graphics graphics, Gem gem, Point position, Dimension cellSize, Dimension gemSize, boolean isSelected) {
        if (isSelected) {
            graphics.setColor(Color.WHITE);
            graphics.fillOval(position.x, position.y, cellSize.width, cellSize.height);
        }

        graphics.setColor(gemTypeView.getColor(gem.getType()));
        graphics.fillOval(position.x + ((cellSize.width - gemSize.width) / 2),
                position.y + ((cellSize.height - gemSize.height) / 2),
                gemSize.width, gemSize.height);

    }

    public void drawAdvancedGem(Graphics graphics, Gem gem, Point position, Dimension cellSize, Dimension gemSize, boolean isSelected) {
        if (isSelected) {
            graphics.setColor(Color.WHITE);
            graphics.fillOval(position.x, position.y, cellSize.width, cellSize.height);
        }

        graphics.drawImage(gemTypeView.getImage(gem.getType()),
                position.x + ((cellSize.width - gemSize.width) / 2),
                position.y + ((cellSize.height - gemSize.height) / 2), this);
    }

    public void markGemSequences(Graphics graphics) {
        ArrayList<Point> gs = gemField.getGemSequence();
        Point location = new Point();

        //indicate that you have a member of a gemSequence
        if (graphics.getColor() == Color.BLACK) {
            graphics.setColor(Color.YELLOW);
        } else {
            graphics.setColor(Color.BLACK);
        }

        for (int row = 0; row < this.gemField.getRows(); row++) {
            for (int column = 0; column < this.gemField.getColumns(); column++) {
                location.move(column, row);
                if (gs.contains(location)) {
                    graphics.fillRect(fieldPosition.x + (column * cellSize.width) + (cellSize.width / 2),
                            fieldPosition.y + (row * cellSize.height) + (cellSize.height / 2),
                            4, 4);
                }
            }
        }
    }
}
