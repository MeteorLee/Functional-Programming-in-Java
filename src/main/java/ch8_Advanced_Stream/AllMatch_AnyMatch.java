package ch8_Advanced_Stream;

import ch8_Advanced_Stream.model.Order;
import ch8_Advanced_Stream.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static ch8_Advanced_Stream.model.Order.OrderStatus.ERROR;
import static ch8_Advanced_Stream.model.Order.OrderStatus.PROCESSED;

public class AllMatch_AnyMatch {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(3, -4, 2, 7, 9);
        boolean allPositive = numbers.stream()
                .allMatch(number -> number > 0);
        System.out.println("allPositive ? = " + allPositive);

        boolean anyNegative = numbers.stream()
                .anyMatch(number -> number < 0);
        System.out.println("anyNegative ? = " + anyNegative);

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

        boolean areAllUserVerified = users.stream()
                .allMatch(User::isVerified);
        System.out.println("areAllUserVerified ? = " + areAllUserVerified);


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

        List<Order> orders = Arrays.asList(order1, order2, order3, order4);

        // TODO: check if any of orders is in ERROR status

        boolean isAnyOrderInErrorStatus = orders.stream()
                .anyMatch(order -> order.getStatus() == ERROR);
        System.out.println("isAnyOrderInErrorStatus ? = " + isAnyOrderInErrorStatus);

    }
}
