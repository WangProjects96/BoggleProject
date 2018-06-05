public class Options {

	private static int taillePlateau;
	private static int nbJoueurs;
	private static String choisirPlateau;
	private static String lettres;
	private static boolean correct;

	static {
		taillePlateau = 4;
		nbJoueurs = 1;
		choisirPlateau = "n";
		lettres = "";
		correct = false;
	}

	public static void setNbJoueurs() {
		System.out.println("Veuillez rentrer le nombre de joueurs");
		do {
			nbJoueurs = Input.returnInt();
			if (nbJoueurs < 1) {
				System.out.println("Veuillez rentrer un nombre supérieur ou égal à 1");
			}
		} while (nbJoueurs < 1);
	}

	public static void setPlateau() {
		System.out.println("De quelle taille doit etre le plateau?");
		do {
			taillePlateau = Input.returnInt();
			if (taillePlateau < 4) {
				System.out.println("Veuillez rentrer un nombre supérieur ou égal à 4");
			}
		} while (taillePlateau < 4);
		System.out.println("Voulez vous rentrer votre propre plateau? (O/N)");
		do {
			choisirPlateau = Input.returnString();
			if (!choisirPlateau.equalsIgnoreCase("o") && !choisirPlateau.equalsIgnoreCase("n")) {
				System.out.println("Veuillez rentrer 'o' ou 'n'");
			}
		} while (!choisirPlateau.equalsIgnoreCase("o") && !choisirPlateau.equalsIgnoreCase("n"));
		if (choisirPlateau.equalsIgnoreCase("o")) {
			System.out.println("Veuillez rentrer les lettres du plateau");
			System.out.println("Il doit y en avoir " + (taillePlateau * taillePlateau));
			do {
				lettres = Input.returnString();
				if (lettres.length() == (taillePlateau * taillePlateau)) {
					correct = true;
				} else {
					System.out.println("Il faut ni plus ni moins que " + (taillePlateau * taillePlateau) + " lettres");
					correct = false;
				}
			} while (!correct);
		} else {
			System.out.println("Vous avez choisi de prendre un plateau aleatoire");
		}
	}

	public static int getTaillePlateau() {
		return taillePlateau;
	}

	public static int getNbJoueurs() {
		return nbJoueurs;
	}

	public static String getLettres() {
		return lettres;
	}

	public static String getChoisirPlateau() {
		return choisirPlateau;
	}

}
