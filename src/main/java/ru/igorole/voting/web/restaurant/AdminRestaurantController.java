package ru.igorole.voting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.igorole.voting.model.Restaurant;
import ru.igorole.voting.repository.RestaurantRepository;
import ru.igorole.voting.repository.datajpa.CrudMenuRepository;
import ru.igorole.voting.web.AbstractController;

@RequestMapping(ru.igorole.voting.web.restaurant.AdminRestaurantController.REST_URL)
@RestController
public class AdminRestaurantController extends AbstractController<RestaurantRepository, Restaurant> {
    static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    CrudMenuRepository menuRepository;

    public AdminRestaurantController() {
        super(REST_URL);
    }
//
//    @GetMapping(value = "/{id}/menu", produces = MediaType.APPLICATION_JSON_VALUE)
//    public RestaurantTo getTo(@PathVariable("id") int id) {
//        log.info("get {} menu", id);
//        Restaurant restaurant = repository.get(id);
//        checkNotFoundWithId(restaurant, id);
//
//        List<Menu> allByRestaurantAndAndDate = menuRepository.findAllByRestaurantAndAndDate(restaurant, LocalDate.of(2019, 4, 3));
//
//
//        return RestaurantUtil.toRestaurantTo(restaurant);
//    }

}
