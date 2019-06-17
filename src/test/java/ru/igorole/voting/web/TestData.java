package ru.igorole.voting.web;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.igorole.voting.model.Dish;
import ru.igorole.voting.model.Role;
import ru.igorole.voting.model.User;
import ru.igorole.voting.web.json.JsonUtil;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import static ru.igorole.voting.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;


public class TestData {
    public static final int USER_ID = 0;
    public static final int ADMIN_ID = 1;
    public static final Integer DISH_ID_0 = 0;
    public static final Integer DISH_ID_ERROR = 99999;

    public static final User USER = new User(USER_ID, "Пользователь1", "p1", "user1@ya.ru", Collections.singleton(Role.ROLE_USER));
    public static final User ADMIN = new User(ADMIN_ID, "Админ", "p1", "admin@ya.ru", EnumSet.allOf(Role.class));
    public static final Dish DISH1 = new Dish(0, "milk", "300ml");


    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "meals", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "meals", "password").isEqualTo(expected);
    }

    public static ResultMatcher getUserMatcher(User... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, User.class), List.of(expected));
    }

    public static ResultMatcher getUserMatcher(User expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}