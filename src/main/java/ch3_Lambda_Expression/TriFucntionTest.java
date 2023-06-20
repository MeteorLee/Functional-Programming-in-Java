package ch3_Lambda_Expression;

import ch3_Lambda_Expression.util.TriFunction;

public class TriFucntionTest {
    public static void main(String[] args) {
        TriFunction<Integer, Integer, Integer, Integer> addThreeNumbers =
                (Integer x, Integer y, Integer z) -> {
                    return x + y + z;
                };
        int result = addThreeNumbers.apply(1, 2, 3);
        System.out.println("result = " + result);

        TriFunction<Integer, Integer, Integer, Integer> addThreeNumbers2 =
                (x, y, z) -> x + y + z;
    }
}
