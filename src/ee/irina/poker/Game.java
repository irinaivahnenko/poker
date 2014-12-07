package ee.irina.poker;

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
//kaardimängu kaivitaja
public class Game {

	public static void main(String[] args) {
		// This class starts the poker game
		System.out.println("Alusta!");

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
		
	}

}
