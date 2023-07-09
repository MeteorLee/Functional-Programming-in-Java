package ch8_Advanced_Stream;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyCollectors {
    public static void main(String[] args) {

        List<Integer> numberList = Stream.of(3, -5, 3, 5, 4, 6, 5)
                .collect(Collectors.toList());
        System.out.println(numberList);

        Set<Integer> numberSet = Stream.of(3, -5, 3, 5, 4, 6, 5)
                .collect(Collectors.toSet());
        System.out.println(numberSet);

        List<Integer> numberList2 = Stream.of(3, -5, 3, 5, 4, 6, 5)
                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toList()));
        System.out.println(numberList2);

        Set<Integer> numberSet2 = Stream.of(3, -5, 3, 5, 4, 6, 5)
                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toSet()));
        System.out.println(numberSet2);

        Integer sum = Stream.of(3, -5, 3, 5, 4, 6, 5)
                .collect(Collectors.reducing(0, (x, y) -> x + y));
        Integer sum2 = Stream.of(3, -5, 3, 5, 4, 6, 5)
                .collect(Collectors.reducing(10, (x, y) -> x + y));
        System.out.println("sum = " + sum);
        System.out.println("sum2 = " + sum2);
    }
}
