import java.util.ArrayList;
import java.util.Arrays; 
import java.util.Scanner;

public class Bot {

    int maxDepth = 6;
    boolean isDebugOn = false;

    public Bot(int depth, boolean isDebugOn) {
	this.maxDepth = depth;
	this.isDebugOn = isDebugOn;
    }

    public int minimax(Board gameState, int depth) {
	if (gameState.hasPlayerWon(1)) {
	    return 10000000 * (depth + 1);
	}
	if (gameState.hasPlayerWon(2)) {
	    return -10000000 * (depth + 1);
	}
	if (gameState.isDraw()) {
	    return 0;
	}

	if (depth == 0) {
	    return getScore(gameState);
	}

	ArrayList<Integer> possibleMoves = gameState.generatePossibleMoves();

	if (gameState.isFirstPlayerTurn) {
	    int bestScore = Integer.MIN_VALUE;
	    for (int move = 0; move < possibleMoves.size(); move++) {
		bestScore = Math.max(bestScore, minimax(
		    gameState.testMove(possibleMoves.get(move)), depth - 1)
		);
	    }
	    return bestScore;
	}

	int bestScore = Integer.MAX_VALUE;
	for (int move = 0; move < possibleMoves.size(); move++) {
	    bestScore = Math.min(bestScore, minimax(
		gameState.testMove(possibleMoves.get(move)), depth - 1)
	    );
	}
	return bestScore;
    }

    public int playMove(Board gameState, Scanner input) {

	ArrayList<Integer> possibleMoves = gameState.generatePossibleMoves();

	int[] moveEval = new int[7];

	int bestMove = possibleMoves.get(0);
	int bestMoveScore;
	int testMoveScore;

	if (gameState.isFirstPlayerTurn) {
	    bestMoveScore = Integer.MIN_VALUE;
	    for (int i = 0; i < possibleMoves.size(); i++) {
		testMoveScore = minimax(
		    gameState.testMove(possibleMoves.get(i)),
		    maxDepth
		);

		moveEval[possibleMoves.get(i)] = testMoveScore;

		if (testMoveScore > bestMoveScore) {
		    bestMove = possibleMoves.get(i);
		    bestMoveScore = testMoveScore;
		}
	    }
	}
	else {
	    bestMoveScore = Integer.MAX_VALUE;
	    for (int i = 0; i < possibleMoves.size(); i++) {
		testMoveScore = minimax(
		    gameState.testMove(possibleMoves.get(i)),
		    maxDepth
		);

		moveEval[possibleMoves.get(i)] = testMoveScore;

		if (testMoveScore < bestMoveScore) {
		    bestMove = possibleMoves.get(i);
		    bestMoveScore = testMoveScore;
		}
	    }
	}

	if (this.isDebugOn) {
	    for (int i = 0; i < 7; i++) {
		// If move is not possible
		if (!possibleMoves.contains(i)) {
		    continue;
		}

		if (Math.abs(moveEval[i]) >= 10000000) {
		    if (moveEval[i] > 0) {
			int turnsTillMate = (maxDepth - moveEval[i] / 10000000) / 2 + 1;
			System.out.format("Column %d: +M%d%n", i+1, turnsTillMate);
			continue;
		    } else {
			int turnsTillMate = (maxDepth - Math.abs(moveEval[i]) / 10000000) / 2 + 1;
			System.out.format("Column %d: -M%d%n", i+1, turnsTillMate);
			continue;
		    }
		}
		if (moveEval[i] > 0) {
		    System.out.format("Column %d: +%d%n", i+1, moveEval[i]);
		} else {
		    System.out.format("Column %d: %d%n", i+1, moveEval[i]);
		}
	    }
	}
	return bestMove;
    }

    public int getScore(Board gameState) {
	return 0;
    }
}
