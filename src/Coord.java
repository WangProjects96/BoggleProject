public class Coord {

	private int i;
	private int j;

	public Coord(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public int getCoordI() {
		return i;
	}

	public int getCoordJ() {
		return j;
	}

	public void setCoordI(int i) {
		this.i = i;
	}

	public void setCoordJ(int j) {
		this.j = j;
	}

	public Coord add(Coord c) {
		return new Coord(this.i + c.i, this.j + c.j);
	}

}
