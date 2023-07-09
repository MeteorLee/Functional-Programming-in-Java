package ch8_Advanced_Stream;

import ch8_Advanced_Stream.model.Order;
import ch8_Advanced_Stream.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static ch8_Advanced_Stream.model.Order.OrderStatus.*;

public class MaxMinCount {
    public static void main(String[] args) {
        Optional<Integer> max = Stream.of(5, 3, 7, 2)
                .max(Integer::compareTo);
        System.out.println(max.get());

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

        User firstUser = users.stream()
                .min((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .get();

        System.out.println(firstUser);

        long positiveIntegerCount = Stream.of(1, -4, 5, -3, 6)
                .filter(x -> x > 0)
                .count();
        System.out.println(positiveIntegerCount);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        user1.setCreatedAt(now.minusDays(2));
        user2.setCreatedAt(now.minusHours(10));
        user3.setCreatedAt(now.minusHours(1));
        User user4 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@fastcampus.co.kr")
                .setCreatedAt(now.minusHours(27));

        users = List.of(user1, user2, user3, user4);

        long unverifiedUsersIn24Hrs = users.stream()
                .filter(user -> user.getCreatedAt().isAfter(now.minusDays(1)))
                .filter(user -> !user.isVerified())
                .count();

        System.out.println(unverifiedUsersIn24Hrs);

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

        // TODO: find order with highest amount an in ERROR status;
        Order highestAmountOrderInError = orders.stream()
                .filter(order -> order.getStatus() == ERROR)
                .max((o1, o2) -> o1.getAmount().compareTo(o2.getAmount()))
                .get();

        System.out.println(highestAmountOrderInError);

        BigDecimal maxErroredAmount = orders.stream()
                .filter(order -> order.getStatus() == ERROR)
                .map(Order::getAmount)
                .max (BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        System.out.println(maxErroredAmount);

    }
}
