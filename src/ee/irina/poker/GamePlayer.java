package ee.irina.poker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Irina Ivahnenko
 */
public class GamePlayer {
	int points;
	private List<Card> cards;

	// konstruktormeetod on see
	public GamePlayer() {
		points = 20;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public List<Card> getCards(){
		return cards;
	}
}
