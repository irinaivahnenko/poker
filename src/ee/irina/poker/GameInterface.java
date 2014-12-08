package ee.irina.poker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Irina Ivahnenko
 * Kasutatud java Swing tutoriali.
 */

/**
 * @author Irina Ivahnenko
 *
 *
 * Siit algab mäng. Mängu eesmärk on teenida 500 punkti. Alguses saab mängija 20 punkti. Iga käega on võimalik võita
 * või kaotada punkte vastavalt pokkeri kombinatsioonile. Kui mängija kaotab kõik punktid, siis on mang labi. Mang algab
 * programmi kaivitamisel ja loppeb kui mangija on voitnud voi kaotanud. Uue kae saab mangija kysida vajutades nupule
 * "uus kasi".
 *
 */
//Loob akna, extenditakse implementeeritakse interface
public class GameInterface extends JPanel implements ActionListener {
    protected CardImagePanel cardImagePanel;
    protected JButton startButton, newHandButton;
    private GamePlayer player;
    private Deck deck;
    private JLabel handLabel;
    private JLabel pointsLabel;

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

        if (player.points < 2){
            remove(newHandButton);
            remove(startButton);
            remove(handLabel);
            remove(pointsLabel);
            remove(cardImagePanel);
            JLabel endLabel = new JLabel("Game Over");
            add(endLabel);
            revalidate();
            repaint();
            return;
        }


        // Jagame mängijale esimese käe
        deck.shuffle();

        player.points -= 2;
        player.setCards(deck.getHand());

        // Siin võiks printida, mis käsi inimesel on.
        //player.printCards();

        // Hindame kae kombinatsiooni
        RankEvaluator evaluator = new RankEvaluator(player);
        int rank = evaluator.evaluate();

        // Siin trükime välja käe kombinatsiooni.
        System.out.println(RankEvaluator.ranks[rank]);

        //Loon tekstipaneeli selleks, et kuvada kaardi kae kombinatsiooni ekraanil
        if (handLabel != null) remove(handLabel);
        handLabel = new JLabel(RankEvaluator.ranks[rank]);
        add(handLabel);

        if (pointsLabel != null) remove(pointsLabel);
        pointsLabel = new JLabel(String.valueOf(player.points));
        add(pointsLabel);


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
