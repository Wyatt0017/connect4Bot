import java.util.ArrayList;
import java.util.Arrays;

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

    public ArrayList<Integer> generatePossibleMoves() {
	ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

	for (int column = 0; column < this.board[0].length; column++) {
	    if (this.board[0][column] == BLANK_SPACE) {
		possibleMoves.add(column);
	    }
	}

	return possibleMoves;
    }

    public Board testMove(int column) {
	ArrayList<Integer> possibleMoves = this.generatePossibleMoves();

	// Error Handling
	if (!possibleMoves.contains(column)) {
	    throw new IllegalArgumentException("Invalid column given");
	}

	Board testBoard = new Board();
    
	// Make a copy of the current board
	testBoard.board = new int[this.board.length][this.board[0].length];
	for (int row = 0; row < this.board.length; row++) {
	    testBoard.board[row] = Arrays.copyOf(this.board[row], this.board[row].length);
	}

	testBoard.isFirstPlayerTurn = !this.isFirstPlayerTurn;

	for (int row = this.board.length-1; row >= 0; row--) {
	    if (testBoard.board[row][column] == BLANK_SPACE) {
		// Set the current space with the player's piece
		if (this.isFirstPlayerTurn == true) {
		    testBoard.board[row][column] = FIRST_PLAYER_SPACE;
		}
		else {
		    testBoard.board[row][column] = SECOND_PLAYER_SPACE;
		}
		// Break out of the loop
		break;
	    }
	}

	return testBoard;
    }

    public void playMove(int column) {
	Board currentBoard = testMove(column);

	this.board = currentBoard.board;
	this.isFirstPlayerTurn = currentBoard.isFirstPlayerTurn;
    }

    public boolean hasPlayerWon(int player) {
	boolean isWin = false;

	// Check rows for win
	for (int row = 0; row < this.board.length; row++) {
	    for (int col = 0; col < this.board[0].length -3; col++) {
		// 4 in a row
		for (int index = 0; index < 4; index++) {
		    if (this.board[row][col+index] != player) {
			break;
		    }
		    if (index == 3) {
			isWin = true;
		    }
		}
	    }
	}

	// Check cols for win
	for (int row = 0; row < this.board.length -3; row++) {
	    for (int col = 0; col < this.board[0].length; col++) {
		// 4 in a row
		for (int index = 0; index < 4; index++) {
		    if (this.board[row+index][col] != player) {
			break;
		    }
		    if (index == 3) {
			isWin = true;
		    }
		}
	    }
	}

	// Check Diag for win
	for (int row = 0; row < this.board.length -3; row++) {
	    for (int col = 0; col < this.board[0].length -3; col++) {
		// 4 in a row
		for (int index = 0; index < 4; index++) {
		    if (this.board[row+index][col+index] != player) {
			break;
		    }
		    if (index == 3) {
			isWin = true;
		    }
		}
	    }
	}

	return isWin;
    }

    public void printBoard() {
	System.out.println("Current Board:");
	// Print the current board rows
	for (int rows = 0; rows < 6; rows++) {
	    for (int columns = 0; columns < 7; columns++) {
		System.out.print("|"+this.board[rows][columns]);
	    }
	    System.out.println("|");
        }
	System.out.println();
    }
}
