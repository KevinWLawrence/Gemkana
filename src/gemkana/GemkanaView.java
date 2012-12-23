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
    private GemTypeImage gemTypeImages;

    public GemkanaView(Image background) {
        super(background);
//        this.setSize(this.fieldPosition.x + (this.gf.getColumns() * this.cellSize.width) + 1000, 
//                     this.fieldPosition.y + (this.gf.getRows() * this.cellSize.height) + 10);
    }

    @Override
    public void initializeEnvironment() {
        cellSize = new Dimension(60, 60);
        gemSize = new Dimension(50, 50);
        fieldPosition = new Point(190, 20);

        this.gemField = new GemField(8, 8);
        this.grid = new Grid(gemField.getSize().width, gemField.getSize().height, cellSize.width, cellSize.height, fieldPosition, Color.WHITE);
    
        this.gemTypeImages = new GemTypeImage();
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
        /* 
         * TODO - HIGH PRIORITY - do not allow a selection unless the two points
         * are adjacent
         */
        Point location = this.grid.cellPointCalculator(e.getX(), e.getY());
        if ((location.x <= gemField.getColumns()) && (location.y <= gemField.getRows())) {
            if (!gemField.updateSelected(location)) {
                java.awt.Toolkit.getDefaultToolkit().beep();
            }
        }
//        System.out.printf("Grid(%d, %d)\n", gridCoordinate.x, gridCoordinate.y);
    }

    @Override
    public void paintEnvironment(Graphics graphics) {

        ArrayList<Point> gs = gemField.getGemSequence();

        for (int row = 0; row < this.gemField.getRows(); row++) {
            for (int column = 0; column < this.gemField.getColumns(); column++) {
               
//                drawBaseGem(graphics, this.gemField.getGems()[column][row], 
//                            new Point(fieldPosition.x + (column * cellSize.width), fieldPosition.y + (row * cellSize.height)), 
//                            this.cellSize, this.gemSize, 
//                            gemField.isSelected(new Point(column, row)));

                drawAdvancedGem(graphics, this.gemField.getGems()[column][row], 
                            new Point(fieldPosition.x + (column * cellSize.width), fieldPosition.y + (row * cellSize.height)), 
                            this.cellSize, this.gemSize, 
                            gemField.isSelected(new Point(column, row)));


                //indicate that you have a member of a gemSequence
                if (graphics.getColor() == Color.BLACK) {
                    graphics.setColor(Color.YELLOW);
                } else {
                    graphics.setColor(Color.BLACK);
                }

                if (gs.contains(new Point(column, row))) {
                    graphics.fillRect(fieldPosition.x + (column * cellSize.width) + (cellSize.width / 2),
                            fieldPosition.y + (row * cellSize.height) + (cellSize.height / 2),
                            4, 4);

                }
            }
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

        graphics.setColor(GemType.getColor(gem.getType()));
        graphics.fillOval(position.x + ((cellSize.width - gemSize.width) / 2),
                          position.y + ((cellSize.height - gemSize.height) / 2),
                          gemSize.width, gemSize.height);

    }

    public void drawAdvancedGem(Graphics graphics, Gem gem, Point position, Dimension cellSize, Dimension gemSize, boolean isSelected) {
        if (isSelected) {
            graphics.setColor(Color.WHITE);
            graphics.fillOval(position.x, position.y, cellSize.width, cellSize.height);
        }

        graphics.drawImage(this.gemTypeImages.getImage(gem.getType()), 
                           position.x + ((cellSize.width - gemSize.width) / 2),
                           position.y + ((cellSize.height - gemSize.height) / 2), this);
    }
}
