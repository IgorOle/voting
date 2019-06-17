package ru.igorole.voting.util;

import org.springframework.stereotype.Controller;
import ru.igorole.voting.model.Restaurant;
import ru.igorole.voting.to.MenuTo;
import ru.igorole.voting.to.RestaurantTo;
import ru.igorole.voting.to.VoteTo;

import java.util.List;

@Controller
public class RestaurantUtil {
    public static RestaurantTo asTO(Restaurant restaurant, MenuTo menu, List<VoteTo> votes) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), menu, votes);
    }
}
