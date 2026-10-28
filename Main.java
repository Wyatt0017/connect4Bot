import java.util.Scanner;

public class Main {
    public void main() {
	Board game = new Board();
	Bot bot = new Bot();

	Scanner input = new Scanner(System.in);

	game.GameLoop(game::UserMove, bot::playMove, input);

	input.close();

  }
}
