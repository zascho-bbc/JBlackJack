package JBlackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	// JButton's
	protected JButton karte;

	protected JButton beenden;

	protected JButton verlassen;
	protected JButton ass;
	protected JButton starten;
	protected JButton einsatzButton;

	// Jtextfields
	static JTextField einsatzt_feld;

	public JPanel getEinsatz_panel() {
		return einsatz_panel;
	}

	public void setEinsatz_panel(JPanel einsatz_panel) {
		this.einsatz_panel = einsatz_panel;
	}

	// JPanel's
	protected JPanel buttons;
	protected JPanel navigation;
	protected JPanel menu;
	protected JPanel einsatz_Panel;
	protected JPanel kontostand_Panel;
	protected JPanel hand_spieler;
	protected JPanel hand_dealer;
	protected JPanel info;
	protected JPanel einsatz_panel = new JPanel();

	// JLabel's
	protected JLabel einsatz_Label;
	protected JLabel kontostand_Label;
	protected JLabel label;
	protected JLabel kartenwert_spieler;
	protected JLabel kartenwert_dealer;
	protected JLabel befehle;

	// Zähler Initialisierung
	int assHand = 0;
	int i = 0;
	int assSpieler = 0;

	// Gui Klasse
	public Gui() throws MalformedURLException {

		// Panel Objekte erstellen
		menu = new JPanel();
		hand_spieler = new JPanel();
		hand_dealer = new JPanel();
		navigation = new JPanel();
		kontostand_Panel = new JPanel();
		einsatz_Panel = new JPanel();
		info = new JPanel();

		// Label Okjekte erstellen
		kontostand_Label = new JLabel();
		label = new JLabel();
		einsatz_Label = new JLabel();
		kartenwert_spieler = new JLabel();
		kartenwert_dealer = new JLabel();
		befehle = new JLabel();

		// Button Objekte erstellen
		karte = new JButton("Karte nehmen [K]");
		verlassen = new JButton("Verlassen [ESC]");
		beenden = new JButton("Keine Karten nehmen[SPACE]");
		starten = new JButton("Neue Runde starten[ENTER]");
		ass = new JButton("Ass wert verändern[A]");
		einsatzButton = new JButton("Einsetzen[E]");

		// JFrame Eigenschaften
		setSize(1000, 900);
		setTitle("JBlackJack");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// actionListener den Button's hinzuf�gen
		karte.addActionListener(this);
		beenden.addActionListener(this);
		verlassen.addActionListener(this);
		ass.addActionListener(this);
		starten.addActionListener(this);
		einsatzButton.addActionListener(this);
		this.setFocusable(true);
		this.addKeyListener(new KeyListener() {
			@Override public void keyPressed(KeyEvent a) {
				// TODO Auto-generated method stub
				int key = a.getKeyCode();
				if (key == KeyEvent.VK_K) {
					if (karte.isEnabled() == false) {
						befehle.setText("Bitte mache zuerst einen Einsatz");
					} else {
						karteZiehen();
					}
				}
				if (key == KeyEvent.VK_SPACE) {
					if (beenden.isEnabled() == true) {
						keineKarteZiehen();
					} else {
						befehle.setText("Nimm zuerst eine Karte");
					}
				}
				if (key == KeyEvent.VK_ESCAPE) {
					spielVerlassen();
				}
				if (key == KeyEvent.VK_ENTER) {
					if (starten.isEnabled() == true) {
						neueRunde();
					}
				}
				if (key == KeyEvent.VK_E) {
					if (einsatzButton.isEnabled() == true) {
						einsetzen();
					}
				}
				if (key == KeyEvent.VK_A) {
					if (ass.isVisible() == true) {
						assWertAendern();
					}
				}
			}

			@Override public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});

		// Buttons dem BefehleMenu hinzuf�gen

		menu.add(einsatz_panel);
		menu.add(karte, BorderLayout.EAST, FlowLayout.LEFT);
		menu.add(beenden, BorderLayout.SOUTH, FlowLayout.RIGHT);
		menu.add(starten, BorderLayout.EAST);
		menu.add(ass, BorderLayout.EAST);
		menu.setBackground(new Color(10, 108, 3));
		menu.add(label);
		ass.setVisible(false);
		this.add(menu, BorderLayout.SOUTH);

		einsatz_panel.setLayout(new BoxLayout(einsatz_panel, BoxLayout.X_AXIS));
		einsatz_panel.setOpaque(false);
		einsatzt_feld = new JTextField(5);
		einsatz_panel.add(einsatzt_feld);
		einsatz_panel.add(einsatzButton);

		// Eigenschaften des Dealers
		hand_dealer.setBorder(new EmptyBorder(100, 10, 10, 0));
		hand_dealer.setBackground(new Color(10, 108, 3));
		hand_dealer.add(kartenwert_dealer);
		this.add(hand_dealer);

		// Eigenschaften des Spielers
		hand_spieler.setBorder(new EmptyBorder(10, 10, 100, 0));
		hand_spieler.setBackground(new Color(10, 108, 3));
		hand_spieler.add(kartenwert_spieler);
		this.add(hand_spieler);

		// Eigenschaten der Navigation
		navigation.add(verlassen, BorderLayout.EAST);
		navigation.setBackground(new Color(10, 108, 3));
		this.add(navigation, BorderLayout.EAST);

		// Kontostand Eigenschaften
		kontostand_Label.setText("Einsatz: " + String.valueOf(Bank.getEinsatz() + "$" + " \n Kontostand: " + Bank.getKontostand()) + "$");
		kontostand_Panel.add(kontostand_Label, BorderLayout.EAST);
		kontostand_Panel.setBackground(new Color(10, 108, 3));
		befehle.setText("Mach einen Einsatz und nimm dannach eine Karte um das Spiel zu beginnen");
		kontostand_Panel.add(befehle);

		this.add(kontostand_Panel, BorderLayout.WEST);

		// Eigenschaften des Info Panels
		info.add(befehle);
		info.setBackground(new Color(255, 0, 0));
		this.add(info, BorderLayout.NORTH);

		if (Spieler.getspielerKartenwert(0) > 0 && Dealer.getdealerKartenwert(0) > 0) {
			beenden.setEnabled(true);
		}

		starten.setEnabled(false);
		beenden.setEnabled(false);
		karte.setEnabled(false);
	}

	public static void main(String[] args) throws MalformedURLException {
		Kartenstapel.stapelGenerieren();
		Gui bl = new Gui();
		bl.setVisible(true);
	}

	public void assWertAendern() {
		Spieler.setspielerKartenwert(-10);
		kartenwert_spieler.setText(String.valueOf("Deine Hand hat einen Wert von: " + Spieler.getspielerKartenwert(0)));
		assSpieler--;
		karte.setEnabled(true);
		beenden.setEnabled(true);
		i = 0;
		if (assSpieler == 0) {
			ass.setVisible(false);
		}
	}

	public void einsetzen() {
		if (Bank.getKontostand() >= Bank.getEinsatz()) {
			einsatzButton.setEnabled(true);
			try {
				int einsatz = Integer.parseInt(einsatzt_feld.getText());
				if (einsatz > Bank.getKontostand()) {
					befehle.setText("Du hast zu wenig Geld");
				} else if (einsatz < 10) {

					befehle.setText("Du musst mindestens 10$ setzen");
				} else {
					karte.setEnabled(true);
					einsatzButton.setEnabled(false);
					befehle.setText("Nimm eine Karte");
					Bank.einsatzErhoehen(einsatz);
					kontostand_Label.setText("Einsatz: " + String.valueOf(Bank.getEinsatz() + "$" + " \n Kontostand: " + Bank.getKontostand()) + "$");

				}
			} catch (NumberFormatException e) {
				befehle.setText("Dein Einsatz muss eine Zahl sein");
				return;
			}
		} else {
			einsatzButton.setEnabled(false);
		}
	}

	public void neueRunde() {
		if (starten.isEnabled() == true) {
			Bank.setEinsatz(0);
			Bank.setEinsatz(Integer.parseInt(Gui.einsatzt_feld.getText()));
			befehle.setText(Bank.gewinnerErmitteln());
			kontostand_Label.setText("Einsatz: " + String.valueOf(Bank.getEinsatz() + "$" + " \n Kontostand: " + Bank.getKontostand() + "$"));
			Kartenstapel.Kartenstappel.clear();
			Kartenstapel.stapelGenerieren();
			Dealer.dealerHand.clear();
			Spieler.spielerHand.clear();

			Container parent = hand_dealer.getParent();
			parent.remove(hand_dealer);
			parent.setBackground(new Color(10, 108, 3));

			Container parent_2 = hand_spieler.getParent();
			parent_2.remove(hand_spieler);
			parent_2.setBackground(new Color(10, 108, 3));

			hand_dealer.removeAll();
			hand_spieler.removeAll();

			hand_dealer.updateUI();
			hand_spieler.updateUI();

			Spieler.getspielerKartenwert(1);
			Dealer.getdealerKartenwert(1);

			einsatzButton.setEnabled(true);
			karte.setEnabled(false);
			beenden.setEnabled(false);
			starten.setEnabled(false);
			info.setVisible(true);
			kartenwert_spieler = new JLabel();
			kartenwert_dealer = new JLabel();
			karte.setEnabled(false);
			einsatzButton.setEnabled(true);

			ass.setVisible(false);
			i = 0;
			assHand = 0;
			assSpieler= 0;
			Spieler.setBlackjack(0);
			
			if (Bank.getKontostand() == 0) {
				int checker = JOptionPane.showConfirmDialog(null, "Sie haben verloren, wollen sie ein neues Spiel starten?", "Verloren", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (checker == JOptionPane.YES_OPTION) {
					Bank.setKontostand(500);
					kontostand_Label.setText("Einsatz: " + String.valueOf(Bank.getEinsatz() + "$" + " \n Kontostand: " + Bank.getKontostand() + "$"));
					befehle.setText("Nimm eine Karte");

					this.add(kontostand_Panel, BorderLayout.WEST);
				} else {
					System.exit(0);
				}
			}
		}
	}

	public void spielVerlassen() {
		int eingabe = JOptionPane.showConfirmDialog(null, "Wollen Sie das Spiel wirklich verlassen?", "Verlassen?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (eingabe == JOptionPane.YES_OPTION) {
			System.exit(0);
		}

	}

	public void keineKarteZiehen() {

		while (Dealer.getdealerKartenwert(0) <= 16) {
			dealerKarteNehmen();
		}

		starten.setEnabled(true);
		beenden.setEnabled(false);
		einsatzButton.setEnabled(false);
		karte.setEnabled(false);
	}

	public void dealerKarteNehmen() {
		if (Dealer.getdealerKartenwert(0) <= 16) {
			Dealer.dealer_kartenehmen();
			if (Kartenstapel.obersteKarte.getName().equals("ace_of_clubs") || Kartenstapel.obersteKarte.getName().equals("ace_of_diamonds")
					|| Kartenstapel.obersteKarte.getName().equals("ace_of_hearts") || Kartenstapel.obersteKarte.getName().equals("ace_of_spades")) {
				assHand++;
			}
			if (Dealer.getdealerKartenwert(0) > 21 && assHand > 0) {
				Dealer.setdealerkKartenwert(-10);
				assHand--;
			}
			final Icon newImageIcon_2 = loadIcon(Kartenstapel.obersteKarte.getName() + ".png");
			JMenuItem newMenuItem_2 = new JMenuItem(newImageIcon_2);
			hand_dealer.add(newMenuItem_2);
			newMenuItem_2.setBackground(new Color(10, 108, 3));
			kartenwert_dealer.setText(String.valueOf("Der Dealer hat eine Hand mit dem Wert: " + Dealer.getdealerKartenwert(0)));
			this.add(hand_dealer);
			hand_dealer.add(kartenwert_dealer);
			revalidate();
			repaint();
			i = 0;
		}
	}

	public void karteZiehen() {
		Bank.setGewinner("");
		befehle.setText("Entscheide ob du noch eine Karte nehmen willst oder nicht");
		/*
		 * Das Kartenstapel wird generiert und die Karte die der Spieler nimmt
		 * wird auf dem Frame abgebildet
		 */

		if (Spieler.getspielerKartenwert(0) <= 21) {
			if (i == 0) {
				Kartenstapel.stapelGenerieren();
				Spieler.spieler_kartenehmen();
				final Icon newImageIcon = loadIcon(Kartenstapel.obersteKarte.getName() + ".png");
				JMenuItem newMenuItem = new JMenuItem(newImageIcon);
				hand_spieler.add(newMenuItem);
				hand_dealer.setBackground(new Color(10, 108, 3));
				kartenwert_spieler.setText(String.valueOf("Deine Hand hat einen Wert von: " + Spieler.getspielerKartenwert(0)));
				hand_dealer.setBackground(new Color(10, 108, 3));
				newMenuItem.setBackground(new Color(10, 108, 3));
				this.add(hand_spieler, BorderLayout.SOUTH);
				this.add(hand_dealer);
				hand_spieler.add(kartenwert_spieler, BorderLayout.WEST);
				i++;
				karte.setEnabled(false);

				if (Kartenstapel.obersteKarte.getName().equals("ace_of_clubs") || Kartenstapel.obersteKarte.getName().equals("ace_of_diamonds")
						|| Kartenstapel.obersteKarte.getName().equals("ace_of_hearts") || Kartenstapel.obersteKarte.getName().equals("ace_of_spades")) {
					assSpieler++;

				}
				if (assSpieler > 0) {
					ass.setVisible(true);
					menu.add(ass);
				}
				revalidate();
				repaint();

				if (Spieler.getspielerKartenwert(0) <= 21) {
					karte.setEnabled(true);
					beenden.setEnabled(true);
					i = 0;
					starten.setEnabled(false);
				} else {

					karte.setEnabled(false);
					beenden.setEnabled(false);
					starten.setEnabled(true);

				}

				einsatzButton.setEnabled(false);
			}
			info.setVisible(true);

		}
		dealerKarteNehmen();

		if (Spieler.getspielerKartenwert(0) > 21 || Dealer.getdealerKartenwert(0) > 21) {
			karte.setEnabled(false);
			beenden.setEnabled(false);
			einsatzButton.setEnabled(false);
			starten.setEnabled(true);
		} else {
			karte.setEnabled(true);
		}

		if (Spieler.getspielerKartenwert(0) == 21 && Spieler.getBlackjack() == 2) {
			URL url = null;
			try {
				url = new URL("http://img1.gbpicsonline.com/gb/64c/017.gif");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Icon icon = new ImageIcon(url);
			JLabel label = new JLabel(icon);

			JFrame f = new JFrame("BLACKJACK!");
			f.getContentPane().add(label);

			f.pack();
			f.setLocationRelativeTo(null);
			f.setVisible(true);
		}
		i = 0;
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(getKarte())) {
			karteZiehen();

		}
		if (ae.getSource().equals(getAufgeben())) {
			keineKarteZiehen();
		}

		if (ae.getSource().equals(getEinsatzButton())) {
			einsetzen();
		}

		if (ae.getSource().equals(getStarten())) {
			neueRunde();
		}
		if (ae.getSource().equals(getVerlassen())) {
			spielVerlassen();
		}
		if (ae.getSource().equals(getAss())) {
			assWertAendern();
		}
	}

	public JButton getStarten() {
		return starten;
	}

	public void setStarten(JButton starten) {
		this.starten = starten;
	}

	private static Icon loadIcon(String iconName) {
		final URL resource = Gui.class.getResource("/images/" + iconName);

		if (resource == null) {
			// TODO Replace by logger
			System.err.println("Error in " + Gui.class.getName() + ": Icon /images/" + iconName + " could not be loaded.");
			return new ImageIcon();
		}
		return new ImageIcon(resource);
	}

	public JButton getKarte() {
		return karte;
	}

	public void setKarte(JButton Karte) {
		karte = Karte;
	}

	public JButton getAufgeben() {
		return beenden;
	}

	public void setAufgeben(JButton aufgeben) {
		beenden = aufgeben;
	}

	public JButton getVerlassen() {
		return verlassen;
	}

	public void setVerlassen(JButton Verlassen) {
		verlassen = Verlassen;
	}

	public JPanel getButtons() {
		return buttons;
	}

	public void setButtons(JPanel Buttons) {
		buttons = Buttons;
	}

	public JPanel getNavigation() {
		return navigation;
	}

	public void setNavigation(JPanel Navigation) {
		navigation = Navigation;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JPanel getPanel() {
		return menu;
	}

	public void setPanel(JPanel panel) {
		this.menu = panel;
	}

	public JButton getBeenden() {
		return beenden;
	}

	public void setBeenden(JButton beenden) {
		this.beenden = beenden;
	}

	public JButton getAss() {
		return ass;
	}

	public void setAss(JButton ass) {
		this.ass = ass;
	}

	public JPanel getMenu() {
		return menu;
	}

	public void setMenu(JPanel menu) {
		this.menu = menu;
	}

	public JPanel getEinsatz_Panel() {
		return einsatz_Panel;
	}

	public void setEinsatz_Panel(JPanel Einsatz_Panel) {
		einsatz_Panel = Einsatz_Panel;
	}

	public JPanel getKontostand_Panel() {
		return kontostand_Panel;
	}

	public void setKontostand_Panel(JPanel Kontostand_Panel) {
		kontostand_Panel = Kontostand_Panel;
	}

	public JPanel getHand_spieler() {
		return hand_spieler;
	}

	public void setHand_spieler(JPanel hand_spieler) {
		this.hand_spieler = hand_spieler;
	}

	public JPanel getHand_dealer() {
		return hand_dealer;
	}

	public void setHand_dealer(JPanel hand_dealer) {
		this.hand_dealer = hand_dealer;
	}

	public JPanel getInfo() {
		return info;
	}

	public void setInfo(JPanel info) {
		this.info = info;
	}

	public JLabel getEinsatz_Label() {
		return einsatz_Label;
	}

	public void setEinsatz_Label(JLabel Einsatz_Label) {
		einsatz_Label = Einsatz_Label;
	}

	public JLabel getKontostand_Label() {
		return kontostand_Label;
	}

	public void setKontostand_Label(JLabel Kontostand_Label) {
		kontostand_Label = Kontostand_Label;
	}

	public JLabel getKartenwert_spieler() {
		return kartenwert_spieler;
	}

	public void setKartenwert_spieler(JLabel kartenwert_spieler) {
		this.kartenwert_spieler = kartenwert_spieler;
	}

	public JLabel getKartenwert_dealer() {
		return kartenwert_dealer;
	}

	public void setKartenwert_dealer(JLabel kartenwert_dealer) {
		this.kartenwert_dealer = kartenwert_dealer;
	}

	public JLabel getGewinner() {
		return befehle;
	}

	public void setGewinner(JLabel gewinner) {
		this.befehle = gewinner;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static JTextField getEinsatzt_feld() {
		return einsatzt_feld;
	}

	public void setEinsatzt_feld(JTextField einsatzt_feld) {
		Gui.einsatzt_feld = einsatzt_feld;
	}

	public JButton getEinsatzButton() {
		return einsatzButton;
	}

	public void setEinsatzButton(JButton einsatzButton) {
		this.einsatzButton = einsatzButton;
	}

}
