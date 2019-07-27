package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import cards.Card;
import cards.Room;
import player.Player;

public class Board {
	private Player player;
	private ArrayList<Room> rooms;
	private ArrayList<Card> cards;

	private String[][] cc = new String[25][24];
	private grid boardGrid;
	private String[][] arrayGrid = new String[50][48];

	public Board() {
		rooms = new ArrayList<Room>();
		cards = new ArrayList<Card>();
		createBoardCells();
	}

	public void setPlayer(String name) {

	}

	public Player getPlayer() {
		return player;
	}

	public boolean addRoom(Room aRoom) {
		boolean wasAdded = false;
		wasAdded = rooms.add(aRoom);
		return wasAdded;
	}

	public boolean removeRoom(Room aRoom) {
		boolean wasRemoved = false;
		wasRemoved = rooms.remove(aRoom);
		return wasRemoved;
	}

	public boolean addCard(Card aCard) {
		boolean wasAdded = false;
		wasAdded = cards.add(aCard);
		return wasAdded;
	}

	public boolean removeCard(Card aCard) {
		boolean wasRemoved = false;
		wasRemoved = cards.remove(aCard);
		return wasRemoved;
	}

	public Room getRoom(int index) {
		Room aRoom = rooms.get(index);
		return aRoom;
	}

	public ArrayList<Room> getRooms() {
		ArrayList<Room> newRooms = (ArrayList<Room>) Collections.unmodifiableList(rooms);
		return newRooms;
	}

	public Card getCard(int index) {
		Card aCard = cards.get(index);
		return aCard;
	}

	public ArrayList<Card> getCards() {
		ArrayList<Card> newCards = (ArrayList<Card>) Collections.unmodifiableList(cards);
		return newCards;
	}

	public boolean hasCards() {
		boolean has = cards.size() > 0;
		return has;
	}

	public void shuffleCards() {

	}

	public void createBoardCells() {
		boardGrid = new grid();
		boardGrid.setGridChar(1,3,3,3,'P', this);
	}
	

	public void applyToGrid() {

	}

	public String toString() {
		return super.toString() + "[" + "]";
	}
}