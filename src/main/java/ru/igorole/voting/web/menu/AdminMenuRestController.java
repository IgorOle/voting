package ru.igorole.voting.web.menu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.igorole.voting.model.Menu;
import ru.igorole.voting.repository.MenuRepository;
import ru.igorole.voting.web.AbstractController;

@RequestMapping(ru.igorole.voting.web.menu.AdminMenuRestController.REST_URL)
@RestController
public class AdminMenuRestController extends AbstractController<MenuRepository, Menu> {
    static final String REST_URL = "/rest/admin/menu";

    public AdminMenuRestController() {
        super(REST_URL);
    }

}
