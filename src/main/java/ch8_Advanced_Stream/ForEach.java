package ch8_Advanced_Stream;

import ch8_Advanced_Stream.model.User;
import ch8_Advanced_Stream.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ForEach {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 2, -1);
        numbers.stream()
                .forEach(System.out::println);
        System.out.println();
        numbers.forEach(System.out::println);
        System.out.println();

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

        EmailService emailService = new EmailService();

        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);


        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println("Do an Operation on user " + user.getName() + " at index " + i);
        }

        IntStream.range(0, users.size()).forEach(i -> {
            User user = users.get(i);
            System.out.println("Do an Operation on user " + user.getName() + " at index " + i);
        });


    }
}
