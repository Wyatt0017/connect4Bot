import java.util.Arrays;

public class Main {
  public static void main() {

    Board test = new Board();

    Board test1 = test.testMove(0);
    System.out.println(Arrays.deepToString(test1.board));
    Board test2 = test1.testMove(0);
    System.out.println(Arrays.deepToString(test2.board));

  }
}
