public class Appli {

	public static void main(String[] args) {
		System.out.println("Bienvenue dans le Boggle!");
		Options.setNbJoueurs();
		Options.setPlateau();
		Boggle b = new Boggle(Options.getNbJoueurs(), Options.getLettres());
		System.out.println(b);
		b.initJoueurs();
		b.rentrerMots();
		b.afficherMotsRentr�s();
		b.supprimerMotsNonAccept�s();
		b.compterPoints();
		b.afficherGagnants();
	}

}
