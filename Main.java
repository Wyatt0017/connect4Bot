import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main() {
	Scanner input = new Scanner(System.in);
	Board game = new Board();
	while (true) {
	    game.printBoard();
	    int userChoice = Integer.parseInt(input.nextLine());
	    game.playMove(userChoice);
	}

  }
}
