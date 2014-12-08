package ee.irina.poker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

        // Siin tuleb teha mangija objekt
        GamePlayer player = new GamePlayer();

        // Siis tuleb teha kaardipakk.
        Deck deck = new Deck();

        // Segame kaardipaki
        deck.shuffle();

        // Jagame mängijale esimese käe
        player.setCards(deck.getHand());

        // Siin võiks printida, mis käsi inimesel on.
        //player.printCards();

        // Hindame kae kombinatsiooni
        RankEvaluator evaluator = new RankEvaluator(player);
        int rank = evaluator.evaluate();

        // Siin trükime välja käe kombinatsiooni.
        System.out.println(RankEvaluator.ranks[rank]);

        //Loon tekstipaneeli selleks, et kuvada kaardi kae kombinatsiooni ekraanil
        JLabel label = new JLabel(RankEvaluator.ranks[rank]);
        newContentPane.add(label);

        //Loon kaartide näitamise ekraanil
        CardImagePanel cardImagePanel = new CardImagePanel(player.getCards());
        newContentPane.add(cardImagePanel);

        //Määran aknale sisu muutujaga newContentPane (private)
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
