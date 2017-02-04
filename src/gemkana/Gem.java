/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gemkana;

/**
 *
 * @author kevin.lawrence
 */
public class Gem /*extends Actor*/ {
//  Test change 0001 KWL
//  Test change 0003 KWL
//  Test change 0004 KWL
    

//  <editor-fold defaultstate="collapsed" desc="Properties">
    private GemType type = GemType.Emerald;

    /**
     * @return the type
     */
    public GemType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(GemType type) {
        this.type = type;
    }

    /**
     * @return the GemType that has been set from a random selection of the
     * supplied GemTypes
     * @param gemTypes the subset of GemTypes to randomly select from
     */
    public GemType randomizeGemType(GemType[] gemTypes) {
        setType(GemType.getRandomGemType(gemTypes));
        return getType();
    }
 
    /**
     * @return the GemType that has been set from a random selection 
     */
    public GemType randomizeGemType() {
        setType(GemType.getRandomGemType());
        return getType();
    }
//  </editor-fold>
    
//  <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Constructor, returns an instance of the Gem class
     * @param type the GemType to set
     */
    public Gem(GemType type) {
        this.type = type;
    }

    //  </editor-fold>
}
