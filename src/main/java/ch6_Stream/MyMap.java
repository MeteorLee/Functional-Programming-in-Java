package ch6_Stream;

import ch6_Stream.model.Order;
import ch6_Stream.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ch6_Stream.model.Order.OrderStatus.*;

public class MyMap {
    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(3, 6, -4);
        Stream<Integer> numberStream = numberList.stream();
        Stream<Integer> numberStreamX2 = numberStream.map(x -> x * 2);
        List<Integer> numberListX2 = numberStreamX2.collect(Collectors.toList());
        System.out.println(numberListX2);

        Stream<Integer> nubmerListStream = numberList.stream();
        Stream<String> strStream = nubmerListStream.map(x -> "Number is " + x);
        List<String> strList = strStream.collect(Collectors.toList());
        System.out.println(strList);

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

        List<String> emailList = users.stream()
                .map(User::getEmailAddress)
                .collect(Collectors.toList());
        System.out.println(emailList);

        Order order1 = new Order()
                .setId(1001)
                .setStatus(CREATED)
                .setCreatedByUserId(101);
        Order order2 = new Order()
                .setId(1002)
                .setStatus(ERROR)
                .setCreatedByUserId(103);
        Order order3 = new Order()
                .setId(1003)
                .setStatus(PROCESSED)
                .setCreatedByUserId(102);
        Order order4 = new Order()
                .setId(1004)
                .setStatus(ERROR)
                .setCreatedByUserId(104);
        Order order5 = new Order()
                .setId(1005)
                .setStatus(IN_PROGRESS)
                .setCreatedByUserId(101);
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        List<Long> createdByUserIds = orders.stream()
                .map(Order::getCreatedByUserId)
                .collect(Collectors.toList());
        System.out.println(createdByUserIds);
    }
}
