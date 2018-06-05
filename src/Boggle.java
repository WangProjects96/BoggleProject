import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Boggle {

	private Joueur[] j;
	private Plateau p;

	public Boggle(int nbJoueurs, String faces) {
		this.j = new Joueur[nbJoueurs];
		if (faces.isEmpty()) {
			this.p = new Plateau();
		} else {
			this.p = new Plateau(faces);
		}
	}

	public Joueur[] getJoueurs() {
		return j;
	}

	public Joueur getJoueur(int i) {
		return j[i];
	}

	public Plateau getPlateau() {
		return p;
	}

	public void initJoueurs() {
		for (int i = 0; i < j.length; i++) {
			System.out.println("Rentrez le nom du joueur " + (i + 1));
			do {
				String nom = Input.returnString();
				nom = nom.toLowerCase();
				nom = nom.substring(0, 1).toUpperCase() + nom.substring(1);
				int verif = 0;
				for (int k = 0; k < i; k++) {
					if (!nom.equalsIgnoreCase(j[k].getNom())) {
						verif++;
					}
				}
				if (verif == i) {
					j[i] = new Joueur(nom);
				} else {
					System.out.println("Veuillez rentrer un nom de joueur different de celui des autres");
				}
			} while (j[i] == null);
		}
		for (int i = 0; i < Options.getNbJoueurs(); i++) {
			System.out.println("Le joueur " + (i + 1) + " sera " + j[i].getNom());
		}
	}

	public void rentrerMots() {
		for (int i = 0; i < j.length; i++) {
			System.out
					.println("C'est au joueur " + j[i].getNom() + " de jouer, rentrez les mots sur une ligne, espacés");
			String mots = Input.returnSpacedString();
			mots = mots.toLowerCase();
			mots = mots.replaceAll(" +", " ");
			j[i].setMotsTrouves(mots);
		}
	}

	public void afficherMotsRentrés() {
		for (int i = 0; i < j.length; i++) {
			j[i].setArrayListData();
			System.out.println("Le joueur " + j[i].getNom() + " a rentré " + j[i].compterMots() + " mots");
			System.out.println("Ce sont les mots suivants: " + j[i].getMotsAVerifier().toString());
		}
	}

	public void supprimerMotsNonAcceptés() {
		supprimerDoublonsIntraListes();
		afficherMotsRestants();
		supprimerDoublonsInterListes();
		afficherMotsRestants();
		supprimerNonPresentsGrille();
		afficherMotsRestants();
		supprimerNonPresentsDico();
		afficherMotsRestants();
	}

	public void supprimerDoublonsIntraListes() {
		System.out.println("Les doublons dans chaque liste seront supprimés");
		int index = 0;
		for (int i = 0; i < j.length; i++) {
			ArrayList<Integer> indexDoublons = new ArrayList<Integer>();
			for (int k = 0; k < j[i].getMotsAVerifier().size(); k++) {
				for (int l = k + 1; l < j[i].getMotsAVerifier().size(); l++) {
					if (j[i].getMotsAVerifier().get(k).equalsIgnoreCase(j[i].getMotsAVerifier().get(l))) {
						if (!indexDoublons.contains(l)) {
							indexDoublons.add(l);
						}
					}
				}
			}
			for (int l = 0; l < indexDoublons.size(); l++) {
				index = indexDoublons.get(l);
				j[i].getMotsAVerifier().remove(index);
				for (int m = 0; m < indexDoublons.size(); m++) {
					if (indexDoublons.get(m) >= index) {
						indexDoublons.set(m, indexDoublons.get(m) - 1);
					}
				}
			}
		}
	}

	public void supprimerDoublonsInterListes() {
		System.out.println("Les mots présents dans plusieures listes seront supprimés");
		int index = 0;
		ArrayList<ArrayList<Integer>> groupesIndexesDoublons = new ArrayList<ArrayList<Integer>>(j.length);
		for (int i = 0; i < j.length; i++) {
			ArrayList<Integer> liste = new ArrayList<Integer>(j[i].getMotsAVerifier().size());
			groupesIndexesDoublons.add(liste);
		}
		for (int i = 0; i < j.length; i++) {
			for (int k = i + 1; k < j.length; k++) {
				for (int l = 0; l < j[i].getMotsAVerifier().size(); l++) {
					for (int m = 0; m < j[k].getMotsAVerifier().size(); m++) {
						if (j[i].getMotsAVerifier().get(l).equalsIgnoreCase(j[k].getMotsAVerifier().get(m))) {
							if (!groupesIndexesDoublons.get(i).contains(l)) {
								groupesIndexesDoublons.get(i).add(l);
							}
							if (!groupesIndexesDoublons.get(k).contains(m)) {
								groupesIndexesDoublons.get(k).add(m);
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < groupesIndexesDoublons.size(); i++) {
			for (int k = 0; k < groupesIndexesDoublons.get(i).size(); k++) {
				index = groupesIndexesDoublons.get(i).get(k);
				j[i].getMotsAVerifier().remove(index);
				for (int l = 0; l < groupesIndexesDoublons.get(i).size(); l++) {
					if (groupesIndexesDoublons.get(i).get(l) >= index) {
						groupesIndexesDoublons.get(i).set(l, groupesIndexesDoublons.get(i).get(l) - 1);
					}
				}
			}
		}
	}

	public void supprimerNonPresentsGrille() {
		System.out.println("Les mots non présents dans la grille seront supprimés");
		int index = 0;
		ArrayList<ArrayList<Integer>> groupesIndexesNonPresents = new ArrayList<ArrayList<Integer>>(j.length);
		for (int i = 0; i < j.length; i++) {
			ArrayList<Integer> liste = new ArrayList<Integer>(j[i].getMotsAVerifier().size());
			groupesIndexesNonPresents.add(liste);
		}
		for (int i = 0; i < j.length; i++) {
			for (int k = 0; k < j[i].getMotsAVerifier().size(); k++) {
				if (!p.recherche(j[i].getMotsAVerifier().get(k))) {
					groupesIndexesNonPresents.get(i).add(k);
				}
			}
		}
		for (int i = 0; i < groupesIndexesNonPresents.size(); i++) {
			for (int k = 0; k < groupesIndexesNonPresents.get(i).size(); k++) {
				index = groupesIndexesNonPresents.get(i).get(k);
				j[i].getMotsAVerifier().remove(index);
				for (int l = 0; l < groupesIndexesNonPresents.get(i).size(); l++) {
					if (groupesIndexesNonPresents.get(i).get(l) >= index) {
						groupesIndexesNonPresents.get(i).set(l, groupesIndexesNonPresents.get(i).get(l) - 1);
					}
				}
			}
		}
	}

	public void supprimerNonPresentsDico() {
		System.out.println("Les mots non présents dans le dictionnaire seront supprimés");
		System.out.println("Rentrez l'adresse absolue du dictionnaire en .txt");
		ArrayList<ArrayList<String>> groupesMotsAGarder = new ArrayList<ArrayList<String>>(j.length);
		for (int i = 0; i < j.length; i++) {
			ArrayList<String> liste = new ArrayList<String>(j[i].getMotsAVerifier().size());
			groupesMotsAGarder.add(liste);
		}
		boolean correct = false;
		do {
			String filename = Input.returnAbsoluteFileName();
			try {
				for (int i = 0; i < j.length; i++) {
					for (int k = 0; k < j[i].getMotsAVerifier().size(); k++) {
						Scanner in = new Scanner(new FileInputStream(filename));
						while (in.hasNextLine()) {
							if (in.nextLine().equalsIgnoreCase(j[i].getMotsAVerifier().get(k))) {
								groupesMotsAGarder.get(i).add(j[i].getMotsAVerifier().get(k));
							}
						}
						in.close();
					}
				}
				correct = true;
			} catch (FileNotFoundException e) {
				System.out.println("Impossible d'ouvrir le fichier");
			}
		} while (!correct);
		for (int i = 0; i < groupesMotsAGarder.size(); i++) {
			j[i].setMotsAVerifier(groupesMotsAGarder.get(i));
		}
	}

	public void afficherMotsRestants() {
		for (int i = 0; i < j.length; i++) {
			System.out.println("Voici les mots restants pour le joueur " + j[i].getNom() + " : "
					+ j[i].getMotsAVerifier().toString());
		}
	}

	public void compterPoints() {
		System.out.println("Les points du/des joueurs à présent calculés");
		for (int i = 0; i < j.length; i++) {
			for (int k = 0; k < j[i].getMotsAVerifier().size(); k++) {
				j[i].actualiserNbPoints(j[i].getMotsAVerifier().get(k));
			}
			System.out.println("Le joueur " + j[i].getNom() + " a " + j[i].getNbPoints() + " points");
		}
	}

	public void afficherGagnants() {
		ArrayList<Integer> indexGagnants = new ArrayList<Integer>();
		int pointsMax = 0;
		for (int i = 0; i < j.length; i++) {
			if (j[i].getNbPoints() > pointsMax) {
				indexGagnants.clear();
				pointsMax = j[i].getNbPoints();
				indexGagnants.add(i);
			} else if (j[i].getNbPoints() == pointsMax) {
				indexGagnants.add(i);
			}
		}
		if (indexGagnants.size() == 1) {
			System.out.println("Le gagnant est " + j[indexGagnants.get(0)].getNom() + " avec "
					+ j[indexGagnants.get(0)].getNbPoints() + " points");
		} else {
			System.out.println("Les gagnants sont :");
			for (int i = 0; i < indexGagnants.size(); i++) {
				System.out.println(j[indexGagnants.get(i)].getNom() + " avec " + j[indexGagnants.get(i)].getNbPoints()
						+ " points");
			}
		}
		System.out.println("Fin de la partie, merci d'avoir joué!");
	}

	public String toString() {
		return p.toString();
	}

}
