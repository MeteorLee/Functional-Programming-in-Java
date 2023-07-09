package ch8_Advanced_Stream;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class FindFirst_FindAny {
    public static void main(String[] args) {

        Optional<Integer> anyNegativeIntegerOp = Stream.of(3, 2, -5, -8, -9, -19)
                .filter(x -> x < 0)
                .findAny();
        System.out.println(anyNegativeIntegerOp.get());

        Optional<Integer> firstPositiveInteger = Stream.of(3, 2, -5, -8)
                .filter(x -> x > 0)
                .findFirst();
        System.out.println(firstPositiveInteger.get());

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, -1, -2, -3);

        numbers.stream().parallel()
                .filter(x -> x > 0)
                .findFirst()
                .ifPresent(System.out::println);
        numbers.stream().parallel()
                .filter(x -> x > 0)
                .findAny()
                .ifPresent(System.out::println);



    }
}
