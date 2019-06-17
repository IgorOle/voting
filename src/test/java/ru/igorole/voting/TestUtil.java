package ru.igorole.voting;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ru.igorole.voting.model.User;
import ru.igorole.voting.util.exception.ErrorInfo;
import ru.igorole.voting.util.exception.ErrorType;
import ru.igorole.voting.web.AuthorizedUser;
import ru.igorole.voting.web.json.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtil {

    public static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    //    public static void <T> assertMatch(<T> actual, <T> expected) {
//        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
//    }
//
//    public static <T> void assertMatch(Iterable<T> actual, Object <T>...expected) {
//        assertMatch(actual, Arrays.asList(expected));
//    }
//
//    public static <T> void assertMatch(Iterable<T> actual, Iterable<T> expected) {
//        assertThat(actual).isEqualTo(expected);
//    }


    public static ResultActions print(ResultActions action) throws UnsupportedEncodingException {
        System.out.println(getContent(action.andReturn()));
        return action;
    }

    public static <T> T readFromJsonResultActions(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return readFromJsonMvcResult(action.andReturn(), clazz);
    }

    public static <T> T readFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(result), clazz);
    }

    public static <T> List<T> readListFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValues(getContent(result), clazz);
    }

    public static void mockAuthorize(User user) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(new AuthorizedUser(user), null, user.getRoles()));
    }

    public static RequestPostProcessor userHttpBasic(User user) {
        return SecurityMockMvcRequestPostProcessors.httpBasic(user.getEmail(), user.getPassword());
    }

    public static RequestPostProcessor userAuth(User user) {
        return SecurityMockMvcRequestPostProcessors.authentication(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    }

    public static void checkErrorInfo(ErrorInfo info, String msg) {
        assertThat(info.getType()).isEqualTo(ErrorType.VALIDATION_ERROR);
        assertThat(info.getDetail().contains(msg)).isTrue();
    }
}