package practice;

import java.util.function.BiFunction;

public class BiFunctionGeneration {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (Integer x, Integer y) -> {
            return x + y;
        };
        int result = add.apply(3, 5);
        System.out.println("result = " + result);

        BiFunction<Integer, Integer, Integer> add2 = (x, y) -> x + y;

    }
}
