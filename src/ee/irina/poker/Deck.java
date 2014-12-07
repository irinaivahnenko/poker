package ee.irina.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Irina Ivahnenko
 *
 */

public class Deck {
	private static int Count = 52;

	// hoain kaarte malus ja teen temast klassi muutuja
	private ArrayList<Card> cards;

	// teen konstruktori initsialiseerimiseks
	// + accessmodifier,valjakutsutav meetod(parameetri tyyp, tyybi
	// nimi){funktsioon nt. if..}
	public Deck(){
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i<Card.Suit.values().length; i++) {
			for (int j = 2; j < 15; j++) {
				Card card = new Card(j, Card.Suit.values()[i]);
				cards.add(card);
			}
		}
		this.cards = cards;
	}

	public void shuffle () {
		Collections.shuffle(cards);
	}

	public List<Card> getHand() {
		// võtame 5 esimest kaarti deckist.
		return cards.subList(0, 5);
	}

	// lisan getteri
	public ArrayList<Card> getCards() {
		return cards;
	}
}
