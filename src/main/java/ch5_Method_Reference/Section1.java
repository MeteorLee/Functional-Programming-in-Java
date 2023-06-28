package ch5_Method_Reference;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Section1 {
    public static void main(String[] args) {
        int a = Integer.parseInt("15");
        System.out.println("Integer.parseInt(\"15\") = " + Integer.parseInt("15"));

        Function<String, Integer> str2int = Integer::parseInt;
        System.out.println("str2int.apply(\"15\") = " + str2int.apply("15"));

        System.out.println("==================================================");

        String str = "hello";
        System.out.println("str.equals(\"hello\") = " + str.equals("hello"));
        Predicate<String> equalsToHello = str::equals;
        System.out.println("equalsToHello.test(\"hello\") = " + equalsToHello.test("hello"));

        System.out.println("==================================================");

        System.out.println("calculate(8, 2, (x, y) -> x + y) = " + calculate(8, 2, (x, y) -> x + y));

        System.out.println("calculate(3, 5, Section1::multiply) = " + calculate(3, 5, Section1::multiply));

        Section1 instance = new Section1();

        System.out.println("calculate(5, 3, instance::subtract) = " + calculate(5, 3, instance::subtract));

        instance.myMethod();
    }


    public static int calculate(int x, int y, BiFunction<Integer, Integer, Integer> operator) {
        return operator.apply(x, y);
    }

    public static int multiply(int x, int y) {
        return x * y;
    }

    public int subtract(int x, int y) {
        return x - y;
    }

    public void myMethod() {
        System.out.println("calculate(10, 3, this::subtract) = " + calculate(10, 3, this::subtract));
    }

}
