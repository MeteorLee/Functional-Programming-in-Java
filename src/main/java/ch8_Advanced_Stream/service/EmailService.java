package ch8_Advanced_Stream.service;

import ch8_Advanced_Stream.model.User;

public class EmailService {

    public void sendPlayWithFriendsEmail(User user) {
        user.getEmailAddress().ifPresent(email ->
                System.out.println("sending 'Play With Friends' email to " + email));
    }

    public void sendMakeMoreFriendsEmail(User user) {
        user.getEmailAddress().ifPresent(email ->
                System.out.println("sending 'Make More Friends' email to " + email));
    }

    public void sendVerifyYourEmailEmail(User user) {
        user.getEmailAddress().ifPresent(email -> {
            System.out.println("Sending 'Verify Your Email' email to " + email);
        });
    }

}
