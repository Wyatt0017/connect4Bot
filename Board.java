import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Board {
    public int[][] board;
    public boolean isFirstPlayerTurn;

    public final int BLANK_SPACE = 0;
    public final int FIRST_PLAYER_SPACE = 1;
    public final int SECOND_PLAYER_SPACE = 2;

    @FunctionalInterface
    public interface Player {
	int playMove(Board board);
    }

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

    private boolean hasPlayerWonRow(int r, int c, int player) {
	for (int i = 0; i < 4; i++) {
	    if (this.board[r][c+i] != player) {
		return false;
	    }
	}
	return true;
    }

    private boolean hasPlayerWonColumn(int r, int c, int player) {
	for (int i = 0; i < 4; i++) {
	    if (this.board[r+i][c] != player) {
		return false;
	    }
	}
	return true;
    }

    private boolean hasPlayerWonDiagonalUp(int r, int c, int player) {
	for (int i = 0; i < 4; i++) {
	    if (this.board[r-i][c+i] != player) {
		return false;
	    }
	}
	return true;
    }

    private boolean hasPlayerWonDiagonalDown(int r, int c, int player) {
	for (int i = 0; i < 4; i++) {
	    if (this.board[r+i][c+i] != player) {
		return false;
	    }
	}
	return true;
    }

    public boolean hasPlayerWon(int player) {
	boolean isWin = false;

	// Check rows for win
	for (int row = 0; row < this.board.length; row++) {
	    for (int col = 0; col < this.board[0].length -3; col++) {
		if (hasPlayerWonRow(row, col, player)) {
		    isWin = true;
		}
	    }
	}

	// Check cols for win
	for (int row = 0; row < this.board.length -3; row++) {
	    for (int col = 0; col < this.board[0].length; col++) {
		if (hasPlayerWonColumn(row, col, player)) {
		    isWin = true;
		}
	    }
	}

	// Check Diagonal Up for win
	for (int row = 3; row < this.board.length; row++) {
	    for (int col = 0; col < this.board[0].length -3; col++) {
		if (hasPlayerWonDiagonalUp(row, col, player)) {
		    isWin = true;
		}
	    }
	}

	// Check Diagonal Down for win
	for (int row = 0; row < this.board.length -3; row++) {
	    for (int col = 0; col < this.board[0].length -3; col++) {
		if (hasPlayerWonDiagonalDown(row, col, player)) {
		    isWin = true;
		}
	    }
	}

	return isWin;
    }

    public boolean isDraw() {
	return (
	    !this.hasPlayerWon(1) &&
	    !this.hasPlayerWon(2) &&
	    this.generatePossibleMoves().isEmpty()
	); 
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

    public int getUserInput(Scanner input) {
	System.out.print("What move do you make?: ");
	return Integer.parseInt(input.nextLine());
    }

    public void GameLoop(Player player1, Player player2) {
        Boolean isGameOver = false;
        this.printBoard();

        while (isGameOver == false){
	    if (this.isFirstPlayerTurn) {
		player1.playMove(this);
	    }
	    else {
		player2.playMove(this);
	    }

            isGameOver = this.hasPlayerWon(1) || this.hasPlayerWon(2) || this.isDraw();
        }
    }

	public int UserMove() {
		Scanner input = new Scanner(System.in);
		System.out.print("Please choose a move: ");
		int choice =0;
		while (true) {
			choice = Integer.parseInt(input.nextLine());
			if(choice >= 0 && choice <7){
				break;
			} else {
				System.out.print("Please choose a move: ");
			}
		}
		input.close();
		return choice;
	}
}
