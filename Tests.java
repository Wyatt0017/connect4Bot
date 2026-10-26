import java.util.Arrays;


public class Tests {

    @FunctionalInterface 
    public interface MethodName {
	boolean test();
    }

    public void checkTest(String testName, MethodName methodName) {
	boolean result = methodName.test();
	if (result) {
	    System.out.format("Test \"%s\" Passed%n", testName);
	}
	else {
	    System.out.format("Test \"%s\" Failed!!!!!!!!!!!%n", testName);
	}
    }

    public boolean testBoardInit() {
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

    public void main(String[] args) {
	System.out.println("------------- Start Of Tests --------------------");
	System.out.println();

	// Tests
	checkTest("testBoardInit", () -> testBoardInit());

	System.out.println();
	System.out.println("-------------- End Of Tests ---------------------");
    }
}
