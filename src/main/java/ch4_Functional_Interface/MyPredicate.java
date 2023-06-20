package ch4_Functional_Interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class MyPredicate {
    public static void main(String[] args) {
        Predicate<Integer> isPositive = x -> x > 0;

        System.out.println(isPositive.test(10));
        System.out.println(isPositive.test(-10));

        List<Integer> inputs = Arrays.asList(10, -3, 5, 6, -1, 0);
        System.out.println("Positive number : " + filter(inputs, isPositive));

//        Predicate<Integer> isNegative = isPositive.negate();
//        System.out.println("Negative number : " + filter(inputs, isNegative));

        System.out.println("Non-Positive number : " + filter(inputs, isPositive.negate()));

        System.out.println("Non-Negative number : " + filter(inputs, isPositive.or(x -> x == 0)));

        System.out.println("Positive even number : " + filter(inputs, isPositive.and(x -> x % 2 == 0)));
    }

    public static <T> List<T> filter(List<T> inputs, Predicate<T> condition) {
        List<T> output = new ArrayList<>();
        for (T input : inputs) {
            if (condition.test(input)) {
                output.add(input);
            }
        }
        return output;
    }
}
