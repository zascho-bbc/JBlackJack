package JBlackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Blackjack extends JFrame {

	private static JButton Kartenehmen = new JButton("Karte Ziehen");

	public static void main(String[] args) {
		Blackjack Spiel = new Blackjack();
		Spiel.setSize(800, 700);
		Spiel.setVisible(true);
		Spiel.add(Kartenehmen, BorderLayout.SOUTH);
		Kartenehmen.setPreferredSize(new Dimension(100,100));
		Kartenehmen.setVisible(true);
	}

	public Blackjack() {
		setTitle("JBlackJack");
		getContentPane().setBackground(new Color(8, 138, 8));		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
