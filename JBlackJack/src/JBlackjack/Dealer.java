package JBlackjack;

import java.util.ArrayList;

public class Dealer extends Person {
	private boolean entscheidung;
	static ArrayList<Karte> dealerHand = new ArrayList<Karte>();
	static int dealerkartenwert = 0;
	private static String nummer;

	public boolean entscheiden_dealer() {

		return entscheidung;
	}

	public static void dealer_kartenehmen() {
		Kartenstapel.obersteKarte = Kartenstapel.getKartenstappel().get(0);
		Kartenstapel.getKartenstappel().remove(Kartenstapel.obersteKarte);
		System.out.println("Dealer :");
		System.out.println(getDealerHand());
		System.out.println(getdealerKartenwert());
		System.out.println();
		Bank.gewinnerErmitteln();
	}

public static ArrayList<Karte> getDealerHand() {
		dealerHand.add(Kartenstapel.obersteKarte);
		int wert;
		wert = Kartenstapel.obersteKarte.getWert();
		Person.assUeberpruefung(wert);
		return dealerHand;
	}

	public static void setDealerHand(ArrayList<Karte> dealerHand) {
		Dealer.dealerHand = dealerHand;
	}

	public void neueRunde() {

	}

	public static int getdealerKartenwert() {
		dealerkartenwert = dealerkartenwert + Kartenstapel.obersteKarte.getWert();
		if (dealerkartenwert > 21) {
			dealerkartenwert = 0;
			return dealerkartenwert;
		} else {
			return dealerkartenwert;
		}

	}

	public static void setDealerkartenwert(int dealerkartenwert) {
		Dealer.dealerkartenwert = dealerkartenwert;
	}

}
