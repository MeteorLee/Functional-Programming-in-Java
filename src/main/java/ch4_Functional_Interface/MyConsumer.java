package ch4_Functional_Interface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MyConsumer {
    public static void main(String[] args) {
        Consumer<String> myStringConsumer = (String str) -> {
            System.out.println(str);
        };
        myStringConsumer.accept("hello");
        myStringConsumer.accept("world");

        List<Integer> integerInputs = Arrays.asList(4, 2, 3);
        Consumer<Integer> myIntegerProcessor = x -> System.out.println("Processing integer " + x);

        process(integerInputs, myIntegerProcessor);

        Consumer<Integer> myDifferentIntegerProcessor = x -> System.out.println("Processing integer in different way " + x);

        process(integerInputs, myDifferentIntegerProcessor);

        List<String> stringInputs = Arrays.asList("world", "hello", "!");

        processT(stringInputs, myStringConsumer);

    }

    public static void process(List<Integer> inputs, Consumer<Integer> processor) {
        for (Integer input : inputs) {
            processor.accept(input);
        }
    }

    public static <T> void processT(List<T> inputs, Consumer<T> processor) {
        for (T input : inputs) {
            processor.accept(input);
        }
    }


}
