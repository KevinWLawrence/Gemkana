/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gemkana;

import environment.ApplicationStarter;
import java.awt.Dimension;

/**
 *
 * @author kevin.lawrence
 */
public class Gemkana {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        startApplication();
    }

    private static void startApplication() {
        ApplicationStarter.Run(new String[0], "GemKana!", new Dimension(700, 570), 
                new GemkanaView(image.ResourceTools.loadImageFromResource("resources/MilkyNebula.jpg")));
    }
}
