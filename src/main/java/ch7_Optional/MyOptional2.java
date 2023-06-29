package ch7_Optional;

import ch7_Optional.model.User;

import java.util.Optional;

public class MyOptional2 {
    public static void main(String[] args) {
        Optional<User> maybeUser = Optional.ofNullable(maybeGetUser(true));
        maybeUser.ifPresent(user -> System.out.println(user));

        Optional<Integer> maybeId = Optional.ofNullable(maybeGetUser(true))
                .map(User::getId);
        maybeId.ifPresent(System.out::println);

        String userName = Optional.ofNullable(maybeGetUser(false))
                .map(User::getName)
                .map(name -> "The name is " + name)
                .orElse("Name is empty");

        System.out.println(userName);

        Optional<Optional<String>> maybeEmail = Optional.ofNullable(maybeGetUser(false))
                .map(User::getEmailAddress);

        Optional<String> maybeEmail2 = Optional.ofNullable(maybeGetUser(false))
                .flatMap(User::getEmailAddress);
        maybeEmail2.ifPresent(System.out::println);

    }

    public static User maybeGetUser(boolean returnUser) {
        if (returnUser) {
            return new User()
                    .setId(1001)
                    .setName("Alice")
                    .setVerified(false);
        }
        return null;
    }
}
