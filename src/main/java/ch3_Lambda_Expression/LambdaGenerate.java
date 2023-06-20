package ch3_Lambda_Expression;

import java.util.function.Function;

public class LambdaGenerate {
    public static void main(String[] args) {

        Function<Integer, Integer> lambdaAdder1 = (Integer x) -> {
            return x + 10;
        };
        int result1 = lambdaAdder1.apply(5);
        System.out.println("result = " + result1);

        Function<Integer, Integer> lambdaAdder2 = x -> x + 10;
        int result2 = lambdaAdder2.apply(5);
        System.out.println("result1 = " + result2);

    }
}
