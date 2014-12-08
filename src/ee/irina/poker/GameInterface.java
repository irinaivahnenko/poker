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
    protected CardImagePanel cardImagePanel;
    protected JButton startButton, newHandButton;
    private GamePlayer player;
    private Deck deck;
    private JLabel label;

    public GameInterface() {
        startButton = new JButton("Alusta");
        startButton.setActionCommand("Alusta");
        startButton.addActionListener(this);

        newHandButton = new JButton("Uus käsi");
        newHandButton.setActionCommand("Uus");
        newHandButton.addActionListener(this);

        add(startButton);
        add(newHandButton);

    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Poker gameby Irina Ivahnenko");
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameInterface newContentPane = new GameInterface();

        //Määran aknale sisu muutujaga newContentPane (private)
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        //Teeb akna nähtavaks
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Alusta")){
            startGame();
        }
        else if (actionEvent.getActionCommand().equals("Uus")){
            dealNewHand();
        }
    }

    private void dealNewHand(){
        // Jagame mängijale esimese käe
        deck.shuffle();

        player.setCards(deck.getHand());

        // Siin võiks printida, mis käsi inimesel on.
        //player.printCards();

        // Hindame kae kombinatsiooni
        RankEvaluator evaluator = new RankEvaluator(player);
        int rank = evaluator.evaluate();

        // Siin trükime välja käe kombinatsiooni.
        System.out.println(RankEvaluator.ranks[rank]);

        //Loon tekstipaneeli selleks, et kuvada kaardi kae kombinatsiooni ekraanil
        if (label != null) remove(label);
        label = new JLabel(RankEvaluator.ranks[rank]);
        add(label);

        //Loon kaartide näitamise ekraanil
        if (cardImagePanel != null) remove(cardImagePanel);
        cardImagePanel = new CardImagePanel(player.getCards());
        add(cardImagePanel);

        revalidate();
        repaint();
    }

    private void startGame(){
        // Siin tuleb teha mangija objekt
        player = new GamePlayer();

        // Siis tuleb teha kaardipakk.
        deck = new Deck();

        // Segame kaardipaki
        deck.shuffle();

        dealNewHand();
    }

}
//Loob kaartide paneeli
