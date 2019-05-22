package ru.igorole.voting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.igorole.voting.model.Menu;
import ru.igorole.voting.model.Restaurant;
import ru.igorole.voting.repository.RestaurantRepository;
import ru.igorole.voting.repository.datajpa.CrudMenuRepository;
import ru.igorole.voting.to.RestaurantTo;
import ru.igorole.voting.util.RestaurantUtil;
import ru.igorole.voting.web.AbstractController;

import java.time.LocalDate;
import java.util.List;

import static ru.igorole.voting.util.ValidationUtil.checkNotFoundWithId;


@RequestMapping(ru.igorole.voting.web.restaurant.ProfileRestaurantController.REST_URL)
@RestController
public class ProfileRestaurantController extends AbstractController<RestaurantRepository, Restaurant> {
    static final String REST_URL = "/rest/profile/restaurants";

    @Autowired
    CrudMenuRepository menuRepository;

    public ProfileRestaurantController() {
        super(REST_URL);
    }

    @GetMapping(value = "/{id}/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantTo getMenu(@PathVariable("id") int id,
                                @RequestParam(value = "date", required = false) LocalDate date) {
        log.info("get {} menu", id);
        Restaurant restaurant = repository.get(id);
        checkNotFoundWithId(restaurant, id);

        if (date == null) {
            date = LocalDate.now();
        }
        List<Menu> menus = menuRepository.findAllByRestaurantAndAndDate(restaurant, date);

        return RestaurantUtil.toRestaurantTo(restaurant, menus);
    }

    @DeleteMapping
    @Override
    public void delete(int id) {
        return;
    }

    @PutMapping
    @Override
    public void update(Restaurant object, int id) {
        return;
    }

    @PostMapping
    @Override
    public ResponseEntity create(Restaurant object) {
        return null;
    }
}
