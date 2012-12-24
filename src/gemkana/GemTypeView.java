/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gemkana;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;

/**
 *
 * @author kevin.lawrence
 */
public class GemTypeView {

    private HashMap<GemType, Image> imageMap;

    {
        this.imageMap = new HashMap<>();
        loadImages();
    }

//  <editor-fold desc="Methods" defaultstate="collapsed">    
    /**
     * load images associated with GemTypes; may be overridden by child classes.
     */
    protected void loadImages() {
        this.imageMap.put(GemType.Emerald, image.ResourceTools.loadImageFromResource("resources/EmeraldSquare.gif"));
        this.imageMap.put(GemType.Pearl, image.ResourceTools.loadImageFromResource("resources/PrincessRound.gif"));
        this.imageMap.put(GemType.Ruby, image.ResourceTools.loadImageFromResource("resources/RubySquare.gif"));
        this.imageMap.put(GemType.Diamond, image.ResourceTools.loadImageFromResource("resources/DiamondSquare.gif"));
        this.imageMap.put(GemType.Sapphire, image.ResourceTools.loadImageFromResource("resources/SapphireSquare.gif"));
        this.imageMap.put(GemType.Amethyst, image.ResourceTools.loadImageFromResource("resources/SapphireRound.gif"));
        this.imageMap.put(GemType.Citrine, image.ResourceTools.loadImageFromResource("resources/DiamondRound.gif"));
        this.imageMap.put(GemType.Garnet, image.ResourceTools.loadImageFromResource("resources/RubyRound.gif"));
        this.imageMap.put(GemType.Magic, image.ResourceTools.loadImageFromResource("resources/DiamondHeart.gif"));
    }
    
    
    /**
     * @return an Image associated with a the supplied GemType.
     */
    public Image getImage(GemType gemType) {
        return this.imageMap.get(gemType);
    }

    /**
     * @return a default Color associated with a the supplied GemType.
     */
    public static Color getColor(GemType gemType) {
        switch (gemType) {
            case Emerald:
                return Color.GREEN;
            case Pearl:
                return Color.LIGHT_GRAY;
            case Diamond:
                return Color.WHITE;
            case Ruby:
                return Color.RED;
            case Sapphire:
                return Color.BLUE;
            case Amethyst:
                return Color.MAGENTA;
            case Citrine:
                return Color.YELLOW;
            case Garnet:
                return Color.ORANGE;
            case Magic:
                return Color.PINK;
            default:
                return Color.BLACK;
        }
    }
//  </editor-fold>    

}
