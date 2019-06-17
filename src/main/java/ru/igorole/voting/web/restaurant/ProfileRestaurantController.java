package ru.igorole.voting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.igorole.voting.model.Menu;
import ru.igorole.voting.model.Restaurant;
import ru.igorole.voting.model.Vote;
import ru.igorole.voting.repository.MenuRepository;
import ru.igorole.voting.repository.RestaurantRepository;
import ru.igorole.voting.repository.VoteRepository;
import ru.igorole.voting.to.RestaurantTo;
import ru.igorole.voting.util.MenuUtil;
import ru.igorole.voting.util.RestaurantUtil;
import ru.igorole.voting.util.VoteUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping(ru.igorole.voting.web.restaurant.ProfileRestaurantController.REST_URL)
@RestController
public class ProfileRestaurantController {
    static final String REST_URL = "/rest/profile/restaurants";

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    VoteRepository voteRepository;

    @GetMapping(value = "/restaurantsTO", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getRestaurantsVotesOnDate(@RequestParam(value = "date", required = false) LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        List<Restaurant> restaurants = restaurantRepository.getAll();
        List<Vote> votes = voteRepository.findAllByDate(date);
        List<Menu> menus = menuRepository.getAllByDate(date);

        List<RestaurantTo> restaurantsTO = restaurants.stream().map(r -> (
                        RestaurantUtil.asTO(r,
                                MenuUtil.asTO(menus.stream().filter(m -> m.getRestaurant().getId() == r.getId()).collect(Collectors.toList())),
                                VoteUtil.asTO(votes.stream().filter(v ->
                                        v.getRestaurant().getId() == r.getId()).collect(Collectors.toList())))
                )
        ).collect(Collectors.toList());
        return restaurantsTO;
    }

    @GetMapping(value = "{id}/restaurantTO", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantTo getRestaurantTO(@PathVariable("id") int id, @RequestParam(value = "date", required = false) LocalDate date) {
        Restaurant restaurant = restaurantRepository.get(id);
        if (date == null) {
            date = LocalDate.now();
        }
        return RestaurantUtil.asTO(restaurant,
                MenuUtil.asTO(menuRepository.getAllByRestaurantAndDate(restaurant, date)),
                VoteUtil.asTO(voteRepository.findAllByRestaurantAndDate(restaurant, date))
        );
    }


}
