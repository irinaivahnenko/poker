package ee.irina.poker;

import javax.management.RuntimeErrorException;

/**
 * 
 * @author Irina Ivahnenko
 *
 */

// kaardile tuleb implementeerida Comparable interface selleks, et kaarte saaks võrrelda ja kasutada sellised meetodeid nagu
//	Collections.sort
public class Card implements Comparable {
	//defineerin vaartuse ja mastid: spades=poti, hearts=artu, diamonds=ruutu, clubs=risti
	private int value;
	private Suit suit;

	//+ Suit on siin enum tyybi nimi
	public enum Suit {
		SPADES, HEARTS, DIAMONDS, CLUBS
	}
	//loon konstruktormeetodi
	//+ funktsiooni deklareerimine
	public Card (int value, Suit suit){
		if (value < 2 && value > 14){
			throw new RuntimeErrorException(new Error(), "Ai sa vana p2tu!");
		}
		//viitan Card-ile
		this.value = value;
		this.suit = suit;
	}
	//lisan "getter" meetodi, et saaksin parida kaardi vaartust ja masti.
	public int getValue() {
		return value;
	}
	public Suit getSuit() {
		return suit;
	}

	// compareTo meetod implementeerib meetodi interface'ist Comparable. Kopeeritud funktsionaalsus.
	@Override
	public int compareTo(Object another) {
		Card card = (Card)another;
		// kasutatud short-hand if-else'i
		return this.value < card.value ?  -1: (this.value == card.value ? 0 : 1);
	}

}
