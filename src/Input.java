import java.util.Scanner;

public class Input {

	private static Scanner sc;
	private static int intVal;
	private static String stringVal;
	private static boolean correct;

	static {
		sc = new Scanner(System.in);
		intVal = 0;
		stringVal = "";
		correct = false;
	}

	public static int returnInt() {
		correct = false;
		do {
			try {
				intVal = Integer.parseInt(sc.nextLine());
				correct = true;
			} catch (NumberFormatException e) {
				System.out.println("Veuillez rentrer un nombre entier");
			}
		} while (!correct);
		return intVal;
	}

	public static String returnString() {
		do {
			stringVal = sc.nextLine();
			if (!stringVal.matches("[a-zA-Z]+$")) {
				System.out.println("Veuillez rentrer des lettres uniquement");
			}
		} while (!stringVal.matches("[a-zA-Z]+$"));
		return stringVal;
	}

	public static String returnSpacedString() {
		do {
			stringVal = sc.nextLine();
			if (!stringVal.matches("[a-zA-Z ]+$") || stringVal.trim().isEmpty()) {
				System.out.println("Veuillez rentrer des lettres uniquement");
			}
		} while (!stringVal.matches("[a-zA-Z ]+$") || stringVal.trim().isEmpty());
		return stringVal.trim();
	}

	public static String returnAbsoluteFileName() {
		do {
			stringVal = sc.nextLine();
			if (!stringVal.matches(".*" + ".txt+$")) {
				System.out.println("Veuillez rentrer un chemin absolu de fichier texte");
			}
		} while (!stringVal.matches(".*" + ".txt+$"));
		return stringVal;
	}

}
