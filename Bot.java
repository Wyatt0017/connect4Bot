import java.util.ArrayList;

public class Bot {
    public Board currentBoardState;

    public Bot (Board currentState) {
	this.currentBoardState = currentState;
    }

    public int minimax(Board gameState, int depth, boolean isMaximizingPlayer) {
	if (gameState.hasPlayerWon(1)) {
	    return Integer.MAX_VALUE;
	}
	if (gameState.hasPlayerWon(2)) {
	    return Integer.MIN_VALUE;
	}

	if (depth == 0) {
	    return getScore(gameState);
	}

	ArrayList<Integer> possibleMoves = gameState.generatePossibleMoves();

	if (isMaximizingPlayer) {
	    int bestScore = Integer.MIN_VALUE;
	    for (int move = 0; move < possibleMoves.size(); move++) {
		bestScore = Math.max(bestScore, minimax(
			    gameState.testMove(possibleMoves.get(move)), depth - 1, false)
		);
	    }
	    return bestScore;
	}

	int bestScore = Integer.MAX_VALUE;
	for (int move = 0; move < possibleMoves.size(); move++) {
	    bestScore = Math.min(bestScore, minimax(
		gameState.testMove(possibleMoves.get(move)), depth - 1, true)
	    );
	}
	return bestScore;
    }

    public int getScore(Board gameState) {
	return 0;
    }
}
