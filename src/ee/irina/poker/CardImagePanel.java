package ee.irina.poker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Irina Ivahnenko on 8.12.14.
 */
public class CardImagePanel extends JPanel {
    public CardImagePanel(List<Card> cards){
        try{
            for (int i = 0; i<cards.size();i++){
                String fileName = cards.get(i).getSuit().name().substring(0, 1).toLowerCase() + String.valueOf(cards.get(i).getValue());
                BufferedImage image = ImageIO.read(new File("card_pictures/" + fileName + ".png"));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                add(picLabel);
            }

        } catch(IOException exeption){
            System.out.print("Pilti ei leitud!");

        }
    }
}