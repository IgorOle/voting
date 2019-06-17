package ru.igorole.voting.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.igorole.voting.model.Dish;
import ru.igorole.voting.repository.DishRepository;
import ru.igorole.voting.web.AbstractControllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.igorole.voting.TestUtil.readFromJsonMvcResult;
import static ru.igorole.voting.TestUtil.userHttpBasic;
import static ru.igorole.voting.web.TestData.*;

public class AdminDishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminDishRestController.REST_URL + '/';



    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + DISH_ID_0)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(readFromJsonMvcResult(result, Dish.class)).isEqualTo(DISH1));
    }

    @Test
    void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + DISH_ID_ERROR)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH_ID_0)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
//        dishRe
//        assertThat(actual).isEqualTo(expected)
    }

    @Test
    void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH_ID_ERROR)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }


}
