import java.util.Scanner;

public class Main {
    public void main() {
	Board game = new Board();
	Bot bot = new Bot(6, true);

	Scanner input = new Scanner(System.in);
	while (true) {

	    game.GameLoop(game::UserMove, bot::playMove, input);
	    game.reset();
	}

	// input.close();

  }
}
