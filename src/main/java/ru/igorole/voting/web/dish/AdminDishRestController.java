package ru.igorole.voting.web.dish;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.igorole.voting.model.Dish;
import ru.igorole.voting.repository.DishRepository;
import ru.igorole.voting.web.AbstractController;

@RequestMapping(AdminDishRestController.REST_URL)
@RestController
public class AdminDishRestController extends AbstractController<DishRepository, Dish> {
    static final String REST_URL = "/rest/admin/dishes";

    public AdminDishRestController() {
        super(REST_URL);
    }

}
