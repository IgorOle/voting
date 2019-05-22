package ru.igorole.voting.util;

import org.springframework.stereotype.Controller;
import ru.igorole.voting.model.Dish;
import ru.igorole.voting.model.Menu;
import ru.igorole.voting.model.Restaurant;
import ru.igorole.voting.to.RestaurantTo;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RestaurantUtil {

    public static RestaurantTo toRestaurantTo(Restaurant restaurant, List<Menu> menus) {
        List<Dish> dishes = menus.stream().map(m -> m.getDish()).collect(Collectors.toList());
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), dishes);
    }
}
