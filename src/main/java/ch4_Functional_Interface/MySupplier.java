package ch4_Functional_Interface;

import java.util.function.Supplier;

public class MySupplier {
    public static void main(String[] args) {
        Supplier<String> myStringSupplier = () -> {
            return "hello world!";
        };
        System.out.println(myStringSupplier.get());

        Supplier<Double> myRandomDoubleSupplier = () -> Math.random();
        System.out.println(myRandomDoubleSupplier.get());
        System.out.println(myRandomDoubleSupplier.get());
        System.out.println(myRandomDoubleSupplier.get());
        System.out.println(myRandomDoubleSupplier.get());

        System.out.println("---====================================================");
        System.out.println("---====================================================");

        printRandomDoubles(myRandomDoubleSupplier, 4);

    }

    public static void printRandomDoubles(Supplier<Double> randomSupplier,
                                          int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(randomSupplier.get());
        }
    }

}
