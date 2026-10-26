import java.util.Arrays;
import java.util.ArrayList;


public class Test {

    public void main(String[] args) {
	System.out.println("------------- Start Of Tests --------------------");
	System.out.println();

	// Tests
	checkTest("boardInitTest", () -> boardInitTest());
	checkTest("generatePossibleMovesTest1", () -> generatePossibleMovesTest1());
	checkTest("generatePossibleMovesTest2", () -> generatePossibleMovesTest2());
	checkTest("testMoveTest1", () -> testMoveTest1());
	checkTest("testMoveTest2", () -> testMoveTest2());

	System.out.println();
	System.out.println("-------------- End Of Tests ---------------------");
    }

    @FunctionalInterface 
    public interface UnitTest {
	boolean test();
    }

    public void checkTest(String testName, UnitTest methodName) {
	boolean result = false;

	try {
	    result = methodName.test();
	}
	catch (Exception e) {
	    System.out.format("Test \"%s\" Failed with error: %s%n", testName, e.getMessage());
	    return;
	}
	if (result) {
	    System.out.format("Test \"%s\" Passed%n", testName);
	}
	else {
	    System.out.format("Test \"%s\" Failed!%n", testName);
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

    public boolean generatePossibleMovesTest2() {
	ArrayList<Integer> expectedAvailableMoves = new ArrayList<>(Arrays.asList(0,1,2,4,5,6));

	int[][] testBoard = new int[][]{
	    {0, 0, 0, 2, 0, 0, 0},
	    {0, 0, 0, 1, 0, 0, 0},
	    {0, 0, 0, 2, 0, 0, 0},
	    {0, 0, 0, 1, 0, 0, 0},
	    {0, 0, 0, 2, 0, 0, 0},
	    {0, 0, 0, 1, 0, 0, 0},
	};

	Board test = new Board();
	test.board = testBoard;

	ArrayList<Integer> availableMoves = test.generatePossibleMoves();

	return (
	    expectedAvailableMoves.equals(availableMoves)
	);
    }
    
    public boolean testMoveTest1() {
	int[][] expectedBoardState = new int[][]{
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 1, 0, 0, 0}
	};
	boolean expectedIsFirstPlayerTurn = false;

	Board test1 = new Board();
	Board test2 = test1.testMove(3);

	return (
	    Arrays.deepEquals(test2.board, expectedBoardState) &&
	    test2.isFirstPlayerTurn == expectedIsFirstPlayerTurn
	);
    }

    public boolean testMoveTest2() {
	int[][] expectedBoardState = new int[][]{
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 2, 0, 0, 0},
	    {0, 0, 0, 1, 0, 0, 0},
	};
	boolean expectedIsFirstPlayerTurn = true;

	Board test1 = new Board();
	Board test2 = test1.testMove(3);
	Board test3 = test2.testMove(3);

	return (
	    Arrays.deepEquals(test3.board, expectedBoardState) &&
	    test3.isFirstPlayerTurn == expectedIsFirstPlayerTurn
	);
    }
}
