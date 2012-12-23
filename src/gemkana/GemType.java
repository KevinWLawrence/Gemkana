/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gemkana;

import java.awt.Color;

/**
 *
 * @author kevin.lawrence
 */
public enum GemType {

    Emerald, Pearl, Diamond, Ruby, Sapphire, Amethyst, Citrine, Garnet, Magic;

//    <editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * @return A random GemType from the entire set of GemType enumerations.
     */
    public static GemType getRandomGemType() {
        return getRandomGemType(GemType.values());
    }

    /**
     * @return Given a set of acceptable GemTypes, return a random GemType that
     * belongs to the set. This is useful if the game is using a subset of the
     * available GemTypes, such as might be done for low difficulty game levels.
     */
    public static GemType getRandomGemType(GemType[] gemTypes) {
        return GemType.values()[ (int) (Math.random() * gemTypes.length)];
    }
    
    /**
     * @return a default Color associated with a the supplied GemType.
     */
    public static Color getColor(GemType gemType) {
        switch (gemType){
            case Emerald  : return Color.GREEN;
            case Pearl    : return Color.LIGHT_GRAY;
            case Diamond  : return Color.WHITE;
            case Ruby     : return Color.RED;
            case Sapphire : return Color.BLUE;
            case Amethyst : return Color.MAGENTA;
            case Citrine  : return Color.YELLOW;
            case Garnet   : return Color.ORANGE;
            case Magic    : return Color.PINK;
            default       : return Color.BLACK;
        }
    }
//       </editor-fold>
}
