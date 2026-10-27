import java.util.ArrayList;

public class Bot {
    public Board currentBoardState;

    final int MAX_DEPTH = 8;

    public Bot (Board currentState) {
	this.currentBoardState = currentState;
    }

    public int minimax(Board gameState, int depth) {
	if (gameState.hasPlayerWon(1)) {
	    return Integer.MAX_VALUE;
	}
	if (gameState.hasPlayerWon(2)) {
	    return Integer.MIN_VALUE;
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

    public int playMove(Board gameState) {
	int bestMove = -1;
	int bestMoveScore;
	int testMoveScore;

	ArrayList<Integer> possibleMoves = gameState.generatePossibleMoves();

	if (gameState.isFirstPlayerTurn) {
	    bestMoveScore = Integer.MIN_VALUE;
	    for (int i = 0; i < possibleMoves.size(); i++) {
		testMoveScore = minimax(
		    gameState.testMove(possibleMoves.get(i)),
		    MAX_DEPTH
		);

		if (testMoveScore >= bestMoveScore) {
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
		    MAX_DEPTH
		);

		if (testMoveScore <= bestMoveScore) {
		    bestMove = possibleMoves.get(i);
		    bestMoveScore = testMoveScore;
		}
	    }
	}

	return bestMove;
    }

    public int getScore(Board gameState) {
	return 0;
    }
}
