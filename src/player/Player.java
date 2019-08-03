package player;

import java.util.*;

import cards.Card;

public class Player {
	private String name;
	private char boardName; 
	private Position position;
	private Move move;
	private ArrayList<Card> cards;
	private ArrayList<Card> excludedCards = new ArrayList<>();
	private Scanner scan = new Scanner(System.in);
	private int SUGGESTION = 1, ACCUSATION = 2;

	public Player(String name, Position position, ArrayList<Card> cards) {
		this.name = name;
		this.position = position;
		this.cards = cards;
		this.boardName=generateBoardName();
		this.move = new Move();
	}

	private char generateBoardName() {
		char correctChar = 'x';
		switch (name) {
		case "Miss Scarlett":
			correctChar = 'S';
			break;
		case "Colonel Mustard":
			correctChar = 'M';
			break;
		case "Mrs. White":
			correctChar = 'W';
			break;
		case "Mr. Green":
			correctChar = 'G';
			break;
		case "Mrs. Peacock":
			correctChar = 'p';
			break;
		case "Professor Plum":
			correctChar = 'P';
			break;

		}
		return correctChar;
	}	
	
	public void playerMove(int newY,int newX) {
		//need to check if move is valid (todo)
		
		move.apply(position,newY, newX,boardName);
		
	}
	
	
	public void spawnMove(int row, int col) {
		position.setY(row);
		position.setX(col);
		move.moveSpawnPos(row,col, boardName);
	}
	
	
	
	
	public boolean isValid(int row, int col) {
		return move.isMoveValid(position.getY()+row,position.getX()+col);
	}
	
	
	public char getBoardChar() {return boardName;}// get the board name of player, e.g; Mr. Green = G.

	public String getName() {
		return name;
	}
	public Move getMove() {
		return move;
	}
	public Position getPositon() {
		return position;
	}

	private void acusationOrSuggestion(ArrayList<Card> cards) {
		int choice = -1;

		while (choice != SUGGESTION && choice != ACCUSATION) {
			System.out.println("Make a chioce:");
			System.out.println("1- Suggestion\n" + "2- Accusation");

			try {
				choice = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Please enter a valid number");
			}
		}
		
		chooseCards(cards, choice);
	}
	
	private void chooseCards(ArrayList<Card> cards, int choice) {
		int cardNum = 1;
		
		for(int i = 0; i < cards.size(); i++) {
			if(!excludedCards.contains(cards.get(i))) {
				System.out.println(cardNum++ + "- " + cards.get(i));
			}
		}
		
		int card = scan.nextInt();
	}

	public Card getCard(int index) {
		Card aCard = cards.get(index);
		return aCard;
	}

	public ArrayList<Card> getCards() {
		ArrayList<Card> newCards = (ArrayList<Card>) Collections.unmodifiableList(cards);
		return newCards;
	}

	public int numberOfCards() {
		int number = cards.size();
		return number;
	}

	public boolean hasCards() {
		boolean has = cards.size() > 0;
		return has;
	}

	public int indexOfCard(Card aCard) {
		int index = cards.indexOf(aCard);
		return index;
	}

	public boolean setPositon(Position aNewPositon) {
		boolean wasSet = false;
		if (aNewPositon != null) {
			position = aNewPositon;
			wasSet = true;
		}
		return wasSet;
	}

	public boolean setCards(Card... newCards) {
		boolean wasSet = false;
		ArrayList<Card> verifiedCards = new ArrayList<Card>();
		for (Card aCard : newCards) {
			if (verifiedCards.contains(aCard)) {
				continue;
			}
			verifiedCards.add(aCard);
		}

		if (verifiedCards.size() != newCards.length || verifiedCards.size() != 6) {
			return wasSet;
		}

		cards.clear();
		cards.addAll(verifiedCards);
		wasSet = true;
		return wasSet;
	}

	public String toString() {
		return getName() + " : " + getPositon();
	}
}