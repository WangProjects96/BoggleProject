import java.util.Random;

public class Plateau {

	private static final String[] FACES_POSSIBLES = { "TUPSEL", "MASROI", "GITNEV", "YUNLEG", "DECPAM", "KEOTUN",
			"SERLAC", "LIREWU", "EAATOI", "DESTON", "SIHFEE", "NEHRIS", "TIBRAL", "BOQMAJ", "ZENVAD", "FIXROA" };
	private static int taillePlateau;
	private De[][] Terrain;
	private Boolean[][] Marquage;

	static {
		taillePlateau = Options.getTaillePlateau();
	}

	public Plateau() {
		int k = 0;
		this.Terrain = new De[taillePlateau][taillePlateau];
		for (int i = 0; i < taillePlateau; i++) {
			for (int j = 0; j < taillePlateau; j++) {
				Terrain[i][j] = new De(FACES_POSSIBLES[k]);
				k++;
				if (k >= FACES_POSSIBLES.length) {
					k = 0;
				}
			}
		}
		Random r = new Random();
		int rand[] = { 0, 0 };
		for (int i = 0; i < (taillePlateau); i++) {
			for (int j = 0; j < (taillePlateau); j++) {
				rand[0] = r.nextInt(taillePlateau);
				rand[1] = r.nextInt(taillePlateau);
				De stockage = new De(Terrain[i][j]);
				Terrain[i][j] = Terrain[rand[0]][rand[1]];
				Terrain[rand[0]][rand[1]] = stockage;
			}
		}
		this.Marquage = new Boolean[taillePlateau][taillePlateau];
		for (int i = 0; i < taillePlateau; i++) {
			for (int j = 0; j < taillePlateau; j++) {
				Marquage[i][j] = false;
			}
		}
	}

	public Plateau(String faces) {
		int k = 0;
		this.Terrain = new De[taillePlateau][taillePlateau];
		for (int i = 0; i < taillePlateau; i++) {
			for (int j = 0; j < taillePlateau; j++) {
				Terrain[i][j] = new De(faces.toUpperCase().charAt(k));
				k++;
			}
		}
		this.Marquage = new Boolean[taillePlateau][taillePlateau];
		for (int i = 0; i < taillePlateau; i++) {
			for (int j = 0; j < taillePlateau; j++) {
				Marquage[i][j] = false;
			}
		}
	}

	public static int getTaillePlateau() {
		return taillePlateau;
	}

	public De[][] getTerrain() {
		return Terrain;
	}

	public Boolean[][] getMarquage() {
		return Marquage;
	}

	public boolean recherche(String mot) {
		setMarquageDefaut();
		for (int i = 0; i < taillePlateau; i++) {
			for (int j = 0; j < taillePlateau; j++) {
				Coord coord = new Coord(i, j);
				if (sousRecherche(mot, 0, coord)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean sousRecherche(String mot, int pos, Coord coord) {
		if (pos >= mot.length()) {
			return true;
		}
		if (!existe(coord)) {
			return false;
		}
		if (!correspondance(mot, pos, coord)) {
			return false;
		}
		if (dejaVisitee(coord)) {
			return false;
		}
		setVisitee(coord);
		Coord c = new Coord(0, 0);
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				c.setCoordI(x);
				c.setCoordJ(y);
				if (x == 0 && y == 0) {
					continue;
				}
				if (sousRecherche(mot, pos + 1, coord.add(c))) {
					return true;
				}
			}
		}
		setNonVisitee(coord);
		return false;
	}

	public boolean existe(Coord coord) {
		if (coord.getCoordI() >= 0 && coord.getCoordI() < taillePlateau && coord.getCoordJ() >= 0
				&& coord.getCoordJ() < taillePlateau) {
			return true;
		} else {
			return false;
		}
	}

	public boolean correspondance(String mot, int pos, Coord coord) {
		String face = Character.toString(Terrain[coord.getCoordI()][coord.getCoordJ()].getFaceVisible());
		String lettre = Character.toString(mot.charAt(pos));
		return lettre.equalsIgnoreCase(face);
	}

	public boolean dejaVisitee(Coord coord) {
		return Marquage[coord.getCoordI()][coord.getCoordJ()] == true;
	}

	public void setVisitee(Coord coord) {
		Marquage[coord.getCoordI()][coord.getCoordJ()] = true;
	}

	public void setNonVisitee(Coord coord) {
		Marquage[coord.getCoordI()][coord.getCoordJ()] = false;
	}

	public void setMarquageDefaut() {
		for (int i = 0; i < taillePlateau; i++) {
			for (int j = 0; j < taillePlateau; j++) {
				Marquage[i][j] = false;
			}
		}
	}

	public String toString() {
		String s = new String();
		s = "";
		for (int i = 0; i < taillePlateau; i++) {
			for (int j = 0; j < taillePlateau; j++) {
				s += Terrain[i][j].getFaceVisible();
				if (j < taillePlateau - 1) {
					s = s + " ";
				}
			}
			if (i < taillePlateau - 1) {
				s += System.lineSeparator();
			}
		}
		return s;
	}

}
