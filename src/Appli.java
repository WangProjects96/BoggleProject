public class Appli {

	public static void main(String[] args) {
		System.out.println("Bienvenue dans le Boggle!");
		Options.setNbJoueurs();
		Options.setPlateau();
		Boggle b = new Boggle(Options.getNbJoueurs(), Options.getLettres());
		System.out.println(b);
		b.initJoueurs();
		b.rentrerMots();
		b.afficherMotsRentrés();
		b.supprimerMotsNonAcceptés();
		b.compterPoints();
		b.afficherGagnants();
	}

}
