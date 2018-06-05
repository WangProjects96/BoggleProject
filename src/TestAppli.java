import static org.junit.Assert.*;

import org.junit.Test;

public class TestAppli {

	@Test
	public void test() {
		Options.setNbJoueurs();
		assertTrue(Options.getNbJoueurs() == (int) Options.getNbJoueurs());
		assertTrue(Options.getNbJoueurs() >= 1);

		Options.setPlateau();
		assertTrue(Options.getTaillePlateau() == (int) Options.getTaillePlateau());
		assertTrue(Options.getTaillePlateau() >= 4);
		assertTrue(Options.getChoisirPlateau().equals((String) Options.getChoisirPlateau()));
		assertTrue(
				Options.getChoisirPlateau().equalsIgnoreCase("o") || Options.getChoisirPlateau().equalsIgnoreCase("n"));
		assertTrue(Options.getLettres().equals((String) Options.getLettres()));
		assertTrue(Options.getLettres().length() == 0
				|| Options.getLettres().length() == (Options.getTaillePlateau() * Options.getTaillePlateau()));

		Boggle b = new Boggle(Options.getNbJoueurs(), Options.getLettres());
		assertTrue(Options.getNbJoueurs() == b.getJoueurs().length);
		assertTrue(Options.getTaillePlateau() == Plateau.getTaillePlateau());
		assertTrue((Plateau.getTaillePlateau() * Plateau.getTaillePlateau()) == (b.getPlateau().getTerrain().length)
				* (b.getPlateau().getTerrain().length));
		System.out.println(b);

		b.initJoueurs();
		for (int i = 0; i < b.getJoueurs().length; i++) {
			assertTrue(b.getJoueur(i).getNom().equals((String) b.getJoueur(i).getNom()));
			assertTrue(b.getJoueur(i).getNom().length() >= 1);
		}

		b.rentrerMots();
		for (int i = 0; i < b.getJoueurs().length; i++) {
			assertTrue(b.getJoueur(i).getMotsTrouves().equals((String) b.getJoueur(i).getMotsTrouves()));
			assertTrue(b.getJoueur(i).getMotsTrouves().equals(b.getJoueur(i).getMotsTrouves().trim()));
			assertTrue(b.getJoueur(i).getMotsTrouves().matches("[a-zA-Z ]+$"));
			assertTrue(b.getJoueur(i).getMotsTrouves().length() >= 1);
		}

		b.afficherMotsRentrés();
		for (int i = 0; i < b.getJoueurs().length; i++) {
			assertTrue(b.getJoueur(i).getMotsAVerifier().size() >= 1);
			for (int k = 0; k < b.getJoueur(i).getMotsAVerifier().size(); k++) {
				assertTrue(b.getJoueur(i).getMotsAVerifier().get(k).length() >= 1);
			}
		}

		b.supprimerMotsNonAcceptés();
		b.compterPoints();
		for (int i = 0; i < b.getJoueurs().length; i++) {
			assertTrue(b.getJoueur(i).getNbPoints() == ((int) b.getJoueur(i).getNbPoints()));
			assertTrue(b.getJoueur(i).getNbPoints() >= 0);
		}

		b.afficherGagnants();
	}

}
