package ch6_Stream;

import ch6_Stream.model.Order;
import ch6_Stream.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ch6_Stream.model.Order.OrderStatus.*;

public class MyFilter {
    public static void main(String[] args) {
        Stream<Integer> numberStream = Stream.of(3, -5, 8, 10, -3, 9);
        Stream<Integer> filteredNumberStream = numberStream.filter(x -> x > 0);
        List<Integer> filteredNumbers = filteredNumberStream.collect(Collectors.toList());
        System.out.println(filteredNumbers);

        List<Integer> newFilteredNumbers = Stream.of(3, -5, 8, 10, -3, 9).filter(x -> x > 0).collect(Collectors.toList());
        System.out.println(newFilteredNumbers);

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr");

        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@fastcampus.co.kr");

        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr");

        List<User> users = Arrays.asList(user1, user2, user3);
        List<User> verifiedUsers = users.stream()
                .filter(User::isVerified)
                .collect(Collectors.toList());
        System.out.println(verifiedUsers);

        List<User> unverifiedUsers = users.stream()
                .filter(user -> !user.isVerified())
                .collect(Collectors.toList());
        System.out.println(unverifiedUsers);

        Order order1 = new Order()
                .setId(1001)
                .setStatus(CREATED);
        Order order2 = new Order()
                .setId(1002)
                .setStatus(ERROR);
        Order order3 = new Order()
                .setId(1003)
                .setStatus(PROCESSED);
        Order order4 = new Order()
                .setId(1004)
                .setStatus(ERROR);
        Order order5 = new Order()
                .setId(1005)
                .setStatus(IN_PROGRESS);

        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);
        List<Order> filteredOrders = orders.stream()
                .filter(order -> order.getStatus() == ERROR)
                .collect(Collectors.toList());
        System.out.println(filteredOrders);
    }

}
