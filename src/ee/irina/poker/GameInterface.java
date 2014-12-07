package ee.irina.poker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Irina Ivahnenko
 * Kasutatud java Swing tutoriali.
 */

//Loob akna, extenditakse implementeeritakse interface
public class GameInterface extends JPanel implements ActionListener {
    protected JButton startButton, newHandButton;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    public GameInterface() {
        startButton = new JButton("Alusta");
        startButton.addActionListener(this);

        newHandButton = new JButton("Uus käsi");
        newHandButton.addActionListener(this);

        add(startButton);
        add(newHandButton);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Poker gameby Irina Ivahnenko");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Akna sisu määramine
        GameInterface newContentPane = new GameInterface();
        //sisu peab olema läbipaistev
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //Teeb akna nähtavaks
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }

        });
    }
}
//Loob kaartide paneeli
