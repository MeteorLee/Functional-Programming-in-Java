package ch4_Functional_Interface;

import ch4_Functional_Interface.model.User;

import java.util.*;

public class MyComparator {
    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        users.add(new User(3, "Alice"));
        users.add(new User(1, "Charlie"));
        users.add(new User(5, "Bob"));
        System.out.println(users);

        Comparator<User> idComparator = (User u1, User u2) -> u1.getId() - u2.getId();
        
        Collections.sort(users, idComparator);
        System.out.println("아이디 순서 대로");
        System.out.println(users);

        System.out.println("이름 순서대로");
        Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));
        System.out.println(users);
    }
}
