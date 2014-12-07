package ee.irina.poker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Irina Ivahnenko on 8.12.14.
 */
public class CardImagePanel extends JPanel {
    private BufferedImage image;
    public CardImagePanel(){
        try{
            image = ImageIO.read(new File("card_pictures/pilt.jpg"));

        } catch(IOException exeption){
            System.out.print("Pilti ei leitud!");

        }
    }
}