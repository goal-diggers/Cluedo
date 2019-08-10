package gui;

import javax.swing.*;

import game.Board;
import player.Player;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUIGame extends JFrame {
	JPanel panel = new JPanel();
	Board board = new Board();
	Dimension screenSize;
	int width, height;

	public GUIGame() {
		setTitle("Cluedo");
		// get the size of the screen
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// make the window's width half of the screen's width
		width = (int) (screenSize.width * 0.5);
		// make the window's height 70% of the screen's height
		height = (int) (screenSize.height * 0.7);
		// set the JFrame's size
		setSize(new Dimension(width, height));
		// make the window at the centre of the screen
		setLocationRelativeTo(null);
		// set the JFrame's background colour
		panel.setBackground(Color.WHITE);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// add a menu bar to the top
		this.addMenuBar();
		this.setupGame();

		this.add(panel);
	}

	private void addMenuBar() {
		// create the JMenuBar object first
		JMenuBar menuBar = new JMenuBar();
		// create a JMenu
		JMenu menu = new JMenu("File");
		// create a menu item
		JMenuItem quit = new JMenuItem(new AbstractAction("Quit") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		// add the item to the menu
		menu.add(quit);
		// add the menu to the menu bar
		menuBar.add(menu);
		// set the JFrame's menu bar to the one we just created
		setJMenuBar(menuBar);
	}

	private void setupGame() {
		// add the label first
		JLabel label = new JLabel("How many Players are going to play?");
		label.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(label);

		// buttons for the user to choose the number of players
		JButton[] buttons = new JButton[4];

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			// number of players is from 3 to 6
			buttons[i].setText(Integer.toString(i + 3));
			// align buttons to the center of the window
			buttons[i].setAlignmentX(CENTER_ALIGNMENT);

			buttons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// get the number on the button
					int numOfPlayers = Integer.parseInt(e.getActionCommand());
					// clear the graphics, and update the JFrame
					panel.removeAll();
					panel.updateUI();
					// go to the character selection
					characterSelection(numOfPlayers, 1);
				}
			});

			panel.add(buttons[i]);
		}
	}

	private void characterSelection(int numOfPlayers, final int currentPlayer) {
		// check if all players have chosen a character
		if (numOfPlayers == 0) {
			panel.removeAll();
			panel.updateUI();
			distributeCards();
			return;
		}

		// one radio button for each available character
		ArrayList<JRadioButton> radioButtons = new ArrayList<>();
		ButtonGroup group = new ButtonGroup();

		// ask the current player to choose a character
		String labelTxt = "Player " + currentPlayer + " please choose a character";
		JLabel label = new JLabel(labelTxt);

		// align the label to the center of the window and add it to the panel
		label.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(label);

		// get list of available characters
		Player[] availablePlayers = board.getAvailablePlayers();

		for (int j = 0; j < availablePlayers.length; j++) {
			// add a radio button for each character
			JRadioButton currentButton = new JRadioButton(board.getAvailablePlayers()[j].toString());

			// align buttons to the center of the window, and them it to the panel
			currentButton.setAlignmentX(CENTER_ALIGNMENT);
			radioButtons.add(currentButton);
			group.add(currentButton);
			panel.add(currentButton);
		}

		// button to confirm character selection
		JButton enter = new JButton("enter");
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < radioButtons.size(); i++) {
					// get the selected button
					if (radioButtons.get(i).isSelected()) {
						// add the character that is at the same index as the selected button
						board.addPlayer(availablePlayers[i]);

						// update graphics
						panel.removeAll();
						panel.updateUI();

						// rerun the method for the next player to choose a character
						int remaining = numOfPlayers - 1;
						int nextPlayer = currentPlayer + 1;
						characterSelection(remaining, nextPlayer);
					}
				}
			}
		});

		// align the button to the center of the window
		enter.setAlignmentX(CENTER_ALIGNMENT);

		// add the button to the panel and update graphics
		panel.add(enter);

		panel.updateUI();
	}

	private void distributeCards() {
		board.distributeCards(board.getPlayers().size());
		board.spawnPlayers();
		drawBoard();
	}

	private void drawBoard() {
		this.add(new GUIBoard(board));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			GUIGame game = new GUIGame();
			game.setVisible(true);
		});
	}
}