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
     *
     * @param type the GemType to set
     */
    public Gem(GemType type) {
        this.type = type;
    }

//    /**
//     * Constructor, returns an instance of the Gem class
//     *
//     * @param image the image to set
//     * @param position the position to set
//     * @param velocity the image to set
//     */
//    public Gem(Image image, Point position, Velocity velocity) {
//        super(position, velocity);
//        setImage(image);
//    }
//
//    /**
//     * Constructor, returns an instance of the Actor class
//     *
//     * @param position the position to set
//     * @param velocity the image to set
//     */
//    public Gem(Point position, Velocity velocity) {
//        super(position, velocity);
//    }
//
//    /**
//     * Constructor, returns an instance of the Actor class
//     *
//     * @param position the position to set
//     * @param velocity the image to set
//     * @param angualrVelocity the angular velocity to set
//     * @param angle the initial angle to set
//     */
//    public Gem(Point position, Velocity velocity, int angularVelocity, int angle) {
//        super(position, velocity, angularVelocity, angle);
//    }
    //  </editor-fold>
}
