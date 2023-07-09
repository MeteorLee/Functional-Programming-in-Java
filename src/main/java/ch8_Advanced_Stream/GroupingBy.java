package ch8_Advanced_Stream;

import ch8_Advanced_Stream.model.Order;
import ch8_Advanced_Stream.model.Order.OrderStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static ch8_Advanced_Stream.model.Order.OrderStatus.*;

public class GroupingBy {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 306, 329, 2312, 203, 2, 2);

        Map<Integer, List<Integer>> unitDigitMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10));
        System.out.println("unitDigitMap = " + unitDigitMap);

        Map<Integer, Set<Integer>> unitDigitSet = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10, Collectors.toSet()));
        System.out.println("unitDigitSet = " + unitDigitSet);

        Map<Integer, List<String>> unitDigitStrMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10,
                        Collectors.mapping(number -> "unit digit is " + number, Collectors.toList())));

        System.out.println("unitDigitStrMap = " + unitDigitStrMap);

        Order order1 = new Order()
                .setId(1001L)
                .setAmount(BigDecimal.valueOf(2000))
                .setStatus(CREATED);
        Order order2 = new Order()
                .setId(1002L)
                .setAmount(BigDecimal.valueOf(4000))
                .setStatus(ERROR);
        Order order3 = new Order()
                .setId(1003L)
                .setAmount(BigDecimal.valueOf(3000))
                .setStatus(ERROR);
        Order order4 = new Order()
                .setId(1004L)
                .setAmount(BigDecimal.valueOf(7000))
                .setStatus(PROCESSED);
        List<Order> orders = List.of(order1, order2, order3, order4);

        // TODO: create a map from order status to the list of corresponding orders
        Map<OrderStatus, List<Order>> orderStatusListMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));
        System.out.println("orderStatusListMap = " + orderStatusListMap);

        Map<OrderStatus, BigDecimal> orderStatusToSumOfAmountMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus,
                        Collectors.mapping(Order::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        System.out.println("orderStatusToSumOfAmountMap = " + orderStatusToSumOfAmountMap);

    }
}
