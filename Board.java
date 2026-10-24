import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Board {
  public int[][] board;
  public boolean isFirstPlayerTurn;

  public final int BLANK_SPACE = 0;
  public final int FIRST_PLAYER_SPACE = 1;
  public final int SECOND_PLAYER_SPACE = 2;

  public Board() {
    this.board = new int[6][7];
    this.isFirstPlayerTurn = true;
  }

  public ArrayList<Integer> generateMoves() {
    ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

    for (int column = 0; column < this.board[0].length; column++) {
      if (this.board[0][column] == BLANK_SPACE) {
	possibleMoves.add(column);
      }
    }

    return possibleMoves;
  }
}
