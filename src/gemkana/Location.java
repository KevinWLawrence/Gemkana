/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gemkana;

/**
 *
 * @author kevin.lawrence
 */
public class Location {

//    <editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * @location the Location to compare this object to
     * @return true if the objects are equal, false otherwise; the Locations are
     * considered to be equal if their respective column and row properties are 
     * equal.
     */
    public boolean equals(Location location){
        return ((location.getColumn() == column) && (location.getRow() == row));
    }
    
    /**
     * @return returns true if the locations provided are horizontally or
     * vertically adjacent, and false otherwise.
     *
     * @param location the Location to be tested as adjacent this Location
     */
    public boolean isAdjacent(Location location) {
        return (Math.abs(location.getColumn() - this.column) == 1)
                && (Math.abs(location.getRow() - this.row) == 0)
                || ((Math.abs(location.getColumn() - this.column) == 0)
                && (Math.abs(location.getRow() - this.row) == 1));
    }
 
    /**
     * @param column the column position to move to
     * @param row the row position to move to
     */
    public void move(int column, int row){
        this.column = column;
        this.row = row;
    }
//    </editor-fold>

//    <editor-fold defaultstate="collapsed" desc="Constructors">
    public Location(){ }
    
    public Location(int column, int row){
        this.column = column;
        this.row = row;
    }
//    </editor-fold>

//    <editor-fold defaultstate="collapsed" desc="Properties">
    private int column;
    private int row;

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }
//    </editor-fold>     
}
