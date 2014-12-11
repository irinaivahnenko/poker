package ee.irina.poker;

import java.util.ArrayList;
import java.util.List;

/**Loon mangija
 * @author Irina Ivahnenko
 */
public class GamePlayer {
	int points;
	private List<Card> cards;

	// anname mängijale 20 punkti
	public GamePlayer() {
		points = 20;
	}
	//väärtustab kaardid
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	//tagastab kaardid
	public List<Card> getCards(){
		return cards;
	}
}
