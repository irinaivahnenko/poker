package ee.irina.poker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Kasutatud java Swing tutoriali.
 * @author Irina Ivahnenko
 *
 */

/**
 * Siit algab mang. Mangu eesmark on teenida voita voi kaotada nii mitu punkti kui voimalik. Alguses saab mangija 20 punkti.
 * Iga kaega on voimalik voita punkte vastavalt pokkeri kombinatsioonile.
 * Mangija igakordne panus kaartide/kae saamiseks on 2 punkti.
 * Uue kae saab mangija kysida vajutades nupule "uus käsi".
 * Kui mangija kaotab koik punktid, siis on mang labi.
 * Mang algab programmi kaivitamisel ja loppeb kui mangija on voitnud voi kaotanud.
 *
 */

/**Loob akna jaakna sisesed elemendid ja nende funktsioonid.
 *
 */
//GameInterface ja selle muutujad
public class GameInterface extends JPanel implements ActionListener {
    protected CardImagePanel cardImagePanel;
    protected JButton startButton, newHandButton;
    private GamePlayer player;
    private Deck deck;
    private JLabel handLabel;
    private JLabel pointsLabel;

    //nuppude loomine, konstruktor
    public GameInterface() {
        startButton = new JButton("Alusta");
        startButton.setActionCommand("Alusta");
        // this viitab (sisemisele) klassile
        startButton.addActionListener(this);

        newHandButton = new JButton("Uus käsi");
        newHandButton.setActionCommand("Uus");
        newHandButton.addActionListener(this);

        add(startButton);
        add(newHandButton);

    }

    //akna loomine
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

    //käivitatakse aken (thread/loim)
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }

        });
    }


    //NB! @Override e. ülekirjutamine peab toimuma siis, kui klassi implementeeritakse interface nt. ActionListener.
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Alusta")){
            startGame();
        }
        else if (actionEvent.getActionCommand().equals("Uus")){
            dealNewHand();
        }
    }
    //mangu lopetamine, sisu taas joonistamine
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


        // Jagame mangijale esimese kae
        deck.shuffle();

        player.points -= 2;
        player.setCards(deck.getHand());

        // Hindame kae kombinatsiooni.
        RankEvaluator evaluator = new RankEvaluator(player);
        int rank = evaluator.evaluate();

        // Siin trykime valja kae kombinatsiooni.
        System.out.println(RankEvaluator.ranks[rank]);

        //Loon tekstipaneeli selleks, et kuvada kaardi kae kombinatsiooni ekraanil, emaldan eelmise kae kombinatsiooni.
        if (handLabel != null) remove(handLabel);
        handLabel = new JLabel(RankEvaluator.ranks[rank]);
        add(handLabel);

        //vana punktiseis eemaldatakse, lisatakse uus seis.
        if (pointsLabel != null) remove(pointsLabel);
        pointsLabel = new JLabel(String.valueOf(player.points));
        add(pointsLabel);


        //Loon kaartide näitamise ekraanil.
        if (cardImagePanel != null) remove(cardImagePanel);
        cardImagePanel = new CardImagePanel(player.getCards());
        add(cardImagePanel);

        revalidate();
        repaint();
    }
    //Mangu alustamine
    private void startGame(){
        // Siin tuleb teha mangija objekt
        player = new GamePlayer();

        // Siis tuleb teha kaardipakk.
        deck = new Deck();

        // Segame kaardipaki
        deck.shuffle();

        //jagame kaardid mangijale
        dealNewHand();
    }

}

