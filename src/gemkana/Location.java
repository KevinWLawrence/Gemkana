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
    private int column;
    private int row;
    
//    <editor-fold defaultstate="collapsed" desc="Methods">
    public boolean equals(Location location){
        return ((location.getColumn() == column) && (location.getRow() == row));
    }
//    </editor-fold>

//    <editor-fold defaultstate="collapsed" desc="Constructors">
    public Location(){ }
    
    public Location(int column, int row){
        this.column = column;
        this.row = row;
    }
//    </editor-fold>

//    <editor-fold defaultstate="collapsed" desc="Accessors and mutators">

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
