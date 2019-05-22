package ru.igorole.voting.util;

import org.springframework.stereotype.Component;
import ru.igorole.voting.model.Role;
import ru.igorole.voting.model.User;
import ru.igorole.voting.to.UserTo;

import java.util.Collections;

@Component
public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(userTo.getName(), userTo.getPassword(), userTo.getEmail(), Collections.singleton(Role.ROLE_USER));
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setPassword(userTo.getPassword());
        user.setEmail(userTo.getEmail());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
