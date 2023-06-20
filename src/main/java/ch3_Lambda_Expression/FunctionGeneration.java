package ch3_Lambda_Expression;

import ch3_Lambda_Expression.util.Adder;

import java.util.function.Function;

public class FunctionGeneration {
    public static void main(String[] args) {

        Function<Integer, Integer> myAdder = new Adder();
        int result = myAdder.apply(5);
        System.out.println("result = " + result);

    }
}
