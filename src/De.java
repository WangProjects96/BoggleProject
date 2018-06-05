import java.util.Random;

public class De {

	private char faceVisibleDe;

	public De(String str) {
		Random r = new Random();
		this.faceVisibleDe = str.charAt(r.nextInt(str.length()));
	}

	public De(char c) {
		this.faceVisibleDe = c;
	}

	public De(De de) {
		this.faceVisibleDe = de.faceVisibleDe;
	}

	public char getFaceVisible() {
		return faceVisibleDe;
	}

}
