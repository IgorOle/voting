package ru.igorole.voting.web.restaurant;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.igorole.voting.model.Restaurant;
import ru.igorole.voting.repository.RestaurantRepository;
import ru.igorole.voting.web.AbstractController;

@RequestMapping(ru.igorole.voting.web.restaurant.AdminRestaurantController.REST_URL)
@RestController
public class AdminRestaurantController extends AbstractController<RestaurantRepository, Restaurant> {
    static final String REST_URL = "/rest/admin/restaurants";

    public AdminRestaurantController() {
        super(REST_URL);
    }

}
