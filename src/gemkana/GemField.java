/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gemkana;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kevin.lawrence
 */
public class GemField {

//    <editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Point> selectedList = new ArrayList<Point>();

    /**
     * @return the selected
     */
    public ArrayList<Point> getSelected() {
        return selectedList;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(ArrayList<Point> selected) {
        this.selectedList = selected;
    }

    public void updateSelected(Point location) {
        //if location is already selected, then deselect
        //else add location to list of selected items
        //if ave two selected items, attempt to switch, check for lines, etc.

        Point selected = null;
        for (Point point : selectedList) {
            if (point.equals(location)) {
                selected = point;
            }
        }

        if (selected != null) {
            selectedList.remove(selected);
        } else {
            selectedList.add(location);
        }

        if (selectedList.size() >= 2) {
            System.out.println("SWITCH ATTEMPT INITIATED");
            switchGemLocations(selectedList.get(0), selectedList.get(1));
            System.out.println("SWITCH ATTEMPT COMPLETE");
            selectedList.clear();
        }
    }

    public boolean isSelected(Point location) {
        for (Point point : selectedList) {
            if (point.equals(location)) {
                return true;
            }
        }

        return false;
    }

    private Gem[][] gems;

    /**
     * @return the gems
     */
    public Gem[][] getGems() {
        return gems;
    }

    /**
     * @param gems the gems to set
     */
    public void setGems(Gem[][] gems) {
        this.gems = gems;
    }
    private GemType[] types = null;

    /**
     * @return the types
     */
    public GemType[] getTypes() {
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(GemType[] types) {
        this.types = types;
    }

    /**
     * @return the number of columns in the GemField, or zero if the GemField
     * has not been initialized.
     */
    public int getColumns() {
        if (this.gems != null) {
            return this.gems.length;
        } else {
            return 0;
        }
    }

    /**
     * @return the number of rows in the GemField, or zero if the GemField has
     * not been initialized.
     */
    public int getRows() {
        if (this.gems != null) {
            return this.gems[0].length;
        } else {
            return 0;
        }
    }

    /**
     * @return the size of the gem field, as a Dimension(columns, rows).
     */
    public Dimension getSize() {
        return new Dimension(getColumns(), getRows());
    }

//    </editor-fold>
    
//    <editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Initialize the GemField with a randomized set of new Gems; this method
     * will respect the types (GemType[]): if types has not been set (the
     * variable is null) the entire set of enumerated GemTypes will be used,
     * otherwise, only those GemTypes listed in the types will be used.
     */
    public final void randomize() {
        if (this.gems != null) {
            for (int column = 0; column < getColumns(); column++) {
                for (int row = 0; row < getRows(); row++) {
                    if (this.types == null) {
                        gems[column][row] = new Gem(GemType.getRandomGemType());
                    } else {
                        gems[column][row] = new Gem(GemType.getRandomGemType(types));
                    }
                }
            }
        }
    }

    public void switchGemLocations(Point location_1, Point location_2) {
        if ((location_1 != null) && (location_2 != null)) {

            Gem gem1 = this.gems[location_1.x][location_1.y];
            Gem gem2 = this.gems[location_2.x][location_2.y];

            this.gems[location_1.x][location_1.y] = gem2;
            this.gems[location_2.x][location_2.y] = gem1;
        }
    }

    private int base = 10;
    private int baseMultiplier = 1;
    private int bonusBoundary = 4;
    private int bonusMultiplier = 2;

    /**
     * score = base score + bonus base score = (# gems * base * baseMultipler)
     * bonus = bonusMultiplier * ((base score) ^ ((1 + bonusScoreBoudary - #
     * gems))
     */
    public int scoreGemSequence(ArrayList<Gem> gemSequence) {
        int gemCount = gemSequence.size();
        int baseScore = gemCount * base * baseMultiplier;
        int bonusScore = (int) (bonusMultiplier * (Math.pow(baseScore, (bonusBoundary + 1 - gemCount))));
        return baseScore + bonusScore;
    }

    public ArrayList<Point> getGemSequence() {
        ArrayList<Point> verticalSequence = new ArrayList<>();
        ArrayList<Point> horizontalSequence = new ArrayList<>();
        GemType type;

        for (int column = 0; column < this.getColumns(); column++) {
            for (int row = 0; row < this.getRows(); row++) {
                //Vertical sequence count
                type = this.gems[column][row].getType();
                verticalSequence.add(new Point(column, row));

                //check the gems above the current position
                for (int i = row - 1; i >= 0; i--) {
                    if (this.gems[column][i].getType() == type) {
                        verticalSequence.add(new Point(column, i));
                    } else {
                        break;
                    }
                }

                //check the gems below the current position
                for (int i = row + 1; i < this.getRows(); i++) {
                    if (this.gems[column][i].getType() == type) {
                        verticalSequence.add(new Point(column, i));
                    } else {
                        break;
                    }
                }

                if (verticalSequence.size() < 3) {
                    verticalSequence.clear();
                } 

                //Horizontal sequence count
                horizontalSequence.add(new Point(column, row));

                //check the gems to the left of the current position
                for (int i = column - 1; i >= 0; i--) {
                    if (this.gems[i][row].getType() == type) {
                        horizontalSequence.add(new Point(i, row));
                    } else {
                        break;
                    }
                }

                //check the gems to the right of the current position
                for (int i = column + 1; i < this.getColumns(); i++) {
                    if (this.gems[i][row].getType() == type) {
                        horizontalSequence.add(new Point(i, row));
                    } else {
                        break;
                    }
                }

                if (horizontalSequence.size() < 3) {
                    horizontalSequence.clear();
                }

                // create the union of the two sequences, then check to see if
                // we have more than 3 selected 
                for (Point point : horizontalSequence) {
                    if (!verticalSequence.contains(point)){
                        verticalSequence.add(point);
                    }
                }
                
                if (verticalSequence.size() < 3) {
                    verticalSequence.clear();
                } else {
                    return verticalSequence;
                }
            }
        }

        return verticalSequence;
    }

    
    public boolean sequenceContainsLocation(ArrayList<Point> sequence, Point location) {
        for (Point point : sequence) {
            if (point.equals(location)) {
                return true;
            }
        }
        return false;
    }

    /**
     * TODO - consider randomizePosition method - keep current GemField, but mix
     * positions randomly.
     */
//    </editor-fold>
    
//    <editor-fold defaultstate="collapsed" desc="Constructors">
    public GemField(Gem[][] gems) {
        this.gems = gems;
    }

    public GemField(int columns, int rows) {
        gems = new Gem[columns][rows];
        this.randomize();
    }

    public GemField(int columns, int rows, GemType[] types) {
        this.types = types;
        gems = new Gem[columns][rows];
        this.randomize();
    }
//    </editor-fold>
}
