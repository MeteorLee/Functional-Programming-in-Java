package ch8_Advanced_Stream;

import ch8_Advanced_Stream.model.Order;
import ch8_Advanced_Stream.model.Order.OrderStatus;
import ch8_Advanced_Stream.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ch8_Advanced_Stream.model.Order.OrderStatus.ERROR;
import static ch8_Advanced_Stream.model.Order.OrderStatus.PROCESSED;

public class ToMap {
    public static void main(String[] args) {

        Map<Integer, String> numberMap = Stream.of(3, 5, -4, 2, 6)
                .collect(Collectors.toMap(x -> x, x -> "Number is " + x));
        System.out.println(numberMap);
        System.out.println(numberMap.get(3));

        Map<Integer, String> numberMap2 = Stream.of(3, 5, -4, 2, 6)
                .collect(Collectors.toMap(Function.identity(),x -> "Number is " + x));

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204));
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(204, 205, 206));
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(204, 205, 207));
        List<User> users = Arrays.asList(user1, user2, user3);

        Map<Integer, User> userIdToUserMap = users.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        System.out.println("userIdToUserMap.get(102) = " + userIdToUserMap.get(102));

        Order order1 = new Order()
                .setId(1001L)
                .setAmount(BigDecimal.valueOf(2000))
                .setStatus(PROCESSED);
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

        Map<Long, OrderStatus> orderIdToOrderStatusMap = orders.stream()
                .collect(Collectors.toMap(Order::getId, Order::getStatus));
        System.out.println("orderIdToOrderStatusMap.get(1002) = " + orderIdToOrderStatusMap.get(1002));
        System.out.println("orderIdToOrderStatusMap.get(1002L) = " + orderIdToOrderStatusMap.get(1002L));

    }
}
