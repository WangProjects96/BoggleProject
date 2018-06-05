import java.util.ArrayList;

public class Joueur {

	private String nom;
	private int nbPoints;
	private String motsTrouves;
	private ArrayList<String> motsAVerifier;
	private static final int[] POINTS = { 1, 1, 2, 3, 5, 11 };
	private static final int[] LONGUEUR = { 3, 4, 5, 6, 7, 8 };

	public Joueur(String n) {
		this.nom = n;
		this.nbPoints = 0;
		this.motsTrouves = "";
		this.motsAVerifier = new ArrayList<String>();
	}

	public int getNbPoints() {
		return nbPoints;
	}

	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

	public String getNom() {
		return nom;
	}

	public String getMotsTrouves() {
		return motsTrouves;
	}

	public void setMotsTrouves(String motsTrouves) {
		this.motsTrouves = motsTrouves;
	}

	public ArrayList<String> getMotsAVerifier() {
		return motsAVerifier;
	}

	public void setMotsAVerifier(ArrayList<String> arrayList) {
		this.motsAVerifier = arrayList;
	}

	public void setArrayListData() {
		String[] mots = motsTrouves.split(" ");
		for (int i = 0; i < mots.length; i++) {
			motsAVerifier.add(mots[i]);
		}
	}

	public int compterMots() {
		boolean motPresent = false;
		int nombreMots = 0;
		int finDeLigne = motsTrouves.length() - 1;
		for (int i = 0; i < motsTrouves.length(); i++) {
			if (Character.isLetter(motsTrouves.charAt(i)) && i != finDeLigne) {
				motPresent = true;
			} else if (!Character.isLetter(motsTrouves.charAt(i)) && motPresent) {
				nombreMots++;
				motPresent = false;
			} else if (Character.isLetter(motsTrouves.charAt(i)) && i == finDeLigne) {
				nombreMots++;
			}
		}
		return nombreMots;
	}

	public void actualiserNbPoints(String mot) {
		for (int i = 0; i < LONGUEUR.length; i++) {
			if (mot.length() == LONGUEUR[i]) {
				this.nbPoints += POINTS[i];
			}
		}
		if (mot.length() > LONGUEUR[LONGUEUR.length - 1]) {
			this.nbPoints += POINTS[POINTS.length - 1];
		}
	}
}
