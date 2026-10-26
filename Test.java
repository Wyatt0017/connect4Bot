import java.util.Arrays;
import java.util.ArrayList;


public class Tests {

    @FunctionalInterface 
    public interface UnitTest {
	boolean test();
    }

    public void checkTest(String testName, UnitTest methodName) {
	boolean result = methodName.test();
	if (result) {
	    System.out.format("Test \"%s\" Passed%n", testName);
	}
	else {
	    System.out.format("Test \"%s\" Failed!!!!!!!!!!!%n", testName);
	}
    }

    public boolean boardInitTest() {
	int[][] expectedBoard = new int[][]{
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0}
	};

	boolean expectedIsFirstPlayerTurn = true;

	Board test = new Board();

	return (
	    Arrays.deepEquals(test.board, expectedBoard) &&
	    test.isFirstPlayerTurn == expectedIsFirstPlayerTurn
	);
    }

    public boolean generatePossibleMovesTest1() {
	ArrayList<Integer> expectedAvailableMoves = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6));

	Board test = new Board();
	ArrayList<Integer> availableMoves = test.generatePossibleMoves();

	return (
	    expectedAvailableMoves.equals(availableMoves)
	);
    }

    public void main(String[] args) {
	System.out.println("------------- Start Of Tests --------------------");
	System.out.println();

	// Tests
	checkTest("boardInitTest", () -> boardInitTest());
	checkTest("generatePossibleMovesTest1", () -> generatePossibleMovesTest1());

	System.out.println();
	System.out.println("-------------- End Of Tests ---------------------");
    }
}
