package ee.irina.poker;

import java.util.Collections;
import java.util.List;

/**
 * Antud klass aitab arvutada mangija/playeri kaes olevat viiest kaardist koosneva kombinatsiooni (parimast)0śt 9ńi.
 * Kokku 10 kombinatsiooni
 * @author Irina Ivahnenko
 */
public class RankEvaluator {
	private int rank = 9;
	private GamePlayer player;
	private List<Card> cards;
	public static final String[] ranks = new String[]{
			"Royal Flush",
			"Straight Flush",
			"Four of a kind",
			"Full house",
			"Flush",
			"Straight",
			"Three of a kind",
			"Two pairs",
			"One pair",
			"High card"
	};
	//punktid vastavalt kaardi kombinatioonile
    public static final int[] points = new int [] {
        1000,
         500,
         100,
          50,
          25,
          20,
          10,
           5,
           2,
           0
    };

	/**
	 *
	 * @param player - aluseks voetakse playeri kaes olevat 5 kaarti.
	 * Kaardid sorteeritakse suuruse järjekorras.
	 * Väiksem kaart esimene ja suuremad kaardid järel
	 */
	//konstruktormeetod
	public RankEvaluator(GamePlayer player) {
		this.player = player;
		cards = player.getCards();
		if (cards == null || cards.size() != 5)
			throw new RuntimeException("M2ngijal on vigane kaartide arv!");
		Collections.sort(cards);
	}

	/**
	 * Kontrollib playeri katt yle erinevate kombinatsioonide.
	 *
	 * @return int rank - tagastatakse int vaartus kae kombinatsiooni jaoks, kus 0 on parim ja 9 on kehvem kae kombinatsioon.
	 * Kontroll toimub 0śt 9ńi, ehk parima kae kombinatsiooni olemasolu.
	 */
	public int evaluate(){
		rank = 9;
		if (isRoyalFlush()) {
            player.points += points[0];
            return rank = 0;
        }
		if (isStraightFlush()) {
            player.points += points[1];
            return rank = 1;
        }
		if (isFourOfAKind()) {
            player.points += points[2];
            return rank = 2;
        }
		if (isFullHouse()) {
            player.points += points[3];
            return rank = 3;
        }
		if (isFlush()) {
            player.points += points[4];
            return rank = 4;
        }
		if (isStraight()) {
            player.points += points[5];
            return rank = 5;
        }
		if (isThreeOfAKind()) {
            player.points += points[6];
            return rank = 6;
        }
		int num = numberOfPairs();
		if (num == 2) {
            player.points += points[7];
            rank = 7;
        }
		if (num == 1) {
            player.points += points[8];
            rank = 8;
        }
		return rank;
	}
	//Kas viimane kaart on ass ja esimene kaart on 10? Tegemist on reaga, kus kõrgeim kaart on ass ja nad on ühest mastist.
	private boolean isRoyalFlush(){
		return cards.get(4).getValue() == 14 && cards.get(0).getValue() == 10
				&& isFlush() && isStraight();
	}
	//Kas on mastirida? Rida + mast
	private boolean isStraightFlush(){
		return isFlush() && isStraight();
	}
	// Kas on nelik? I ja IV või II ja V.
	private boolean isFourOfAKind() {
		return cards.get(0).getValue() == cards.get(3).getValue() ||
				cards.get(1).getValue() == cards.get(4).getValue();

	}
	//Kas on maja? Kolmik ja paar. Paar ja kolmik
	private boolean isFullHouse(){
		boolean fullHouse = false;
		if (cards.get(0).getValue() == cards.get(2).getValue() &&
				cards.get(3).getValue() == cards.get(4).getValue()){
			//Jaetakse meelde, milliseid kaarte sul on kaks ja milliseid kolm
			fullHouse = true;
		}
		if (cards.get(0).getValue() == cards.get(1).getValue() &&
				cards.get(2).getValue() == cards.get(4).getValue()) {
			fullHouse = true;
		}
		return fullHouse;
	}
	//Kas on mast? Käin üle mastid.
	private boolean isFlush(){
		Card.Suit suit = cards.get(0).getSuit();
		for (int i = 1; i<cards.size(); i++){
			if (suit != cards.get(i).getSuit()) return false;
		}
		return true;
	}
	//Kas on rida? Võrdleme järjest esimese kaardi vastavust ülejäänud kaartide ja koha vahega. Ericase on siis, kui rida on ässast viieni.
	private boolean isStraight(){
		return
				(cards.get(0).getValue() == cards.get(1).getValue() -1 &&
						cards.get(0).getValue() == cards.get(2).getValue() -2 &&
						cards.get(0).getValue() == cards.get(3).getValue() -3 &&
						cards.get(0).getValue() == cards.get(4).getValue() -4) ||
						(cards.get(0).getValue() == 2 && cards.get(1).getValue() == 3 &&
								cards.get(2).getValue() == 4 && cards.get(3).getValue() == 5 &&
								cards.get(4).getValue() == 14);
	}
	//Kas tegemist on kolmikuga?
	private boolean isThreeOfAKind() {
		return !isFourOfAKind() &&
			cards.get(0).getValue() == cards.get(2).getValue() ||
				cards.get(1).getValue() == cards.get(3).getValue()||
					cards.get(2).getValue() == cards.get(4).getValue();

	}
	// Tagastab mitu paari on kaes.
	private int numberOfPairs(){
		int pairs = 0;
		for (int i = 0; i<4; i++){
			if(hasPair(cards.subList(i, i+2))){
				pairs++;
			}
		}
		return pairs;
	}
	//Funktsioon tagastab kas kaartide hulgas on paar
	private boolean hasPair(List<Card> cards) {
		for(int value=2; value<=14; value++) {
			int count = 0;
			for (int i = 0; i < cards.size(); i++){
				if (cards.get(i).getValue()==value) {
					count++;
				}
			}
			// count peab olema täpselt 2,et oleks paar
			if (count==2) {
				return true;
			}
		}
		return false;
	}
}
