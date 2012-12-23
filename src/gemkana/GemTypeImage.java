/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gemkana;

import java.awt.Image;
import java.util.HashMap;
import image.ResourceTools;

/**
 *
 * @author kevin.lawrence
 */
public class GemTypeImage {
    
    private HashMap<GemType, Image> imageMap;
    
    {
        this.imageMap = new HashMap<>();
        
        //Emerald, Pearl, Diamond, Ruby, Sapphire, Amethyst, Citrine, Garnet, Magic
        
        this.imageMap.put(GemType.Emerald, image.ResourceTools.loadImageFromResource("resources/EmeraldSquare.gif"));
        this.imageMap.put(GemType.Pearl, image.ResourceTools.loadImageFromResource("resources/PrincessRound.gif"));
        this.imageMap.put(GemType.Ruby, image.ResourceTools.loadImageFromResource("resources/RubySquare.gif"));
        this.imageMap.put(GemType.Sapphire, image.ResourceTools.loadImageFromResource("resources/SapphireSquare.gif"));
        this.imageMap.put(GemType.Amethyst, image.ResourceTools.loadImageFromResource("resources/SapphireRound.gif"));
        this.imageMap.put(GemType.Citrine, image.ResourceTools.loadImageFromResource("resources/DiamondRound.gif"));
        this.imageMap.put(GemType.Garnet, image.ResourceTools.loadImageFromResource("resources/RubyRound.gif"));
        this.imageMap.put(GemType.Magic, image.ResourceTools.loadImageFromResource("resources/DiamondHeart.gif"));
    }
    
    public Image getImage(GemType gemType){
        return this.imageMap.get(gemType);
    }
    
}
