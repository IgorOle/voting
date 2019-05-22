package ru.igorole.voting.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.igorole.voting.model.User;
import ru.igorole.voting.repository.UserRepository;
import ru.igorole.voting.util.ValidationUtil;
import ru.igorole.voting.web.AbstractController;

import static ru.igorole.voting.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(AdminUserRestController.REST_URL)
public class AdminUserRestController extends AbstractController<UserRepository, User> {
    static final String REST_URL = "/rest/admin/users";

    @Autowired
    UserRepository userRepository;

    public AdminUserRestController() {
        super(REST_URL);
    }

    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        User user = repository.get(id);
        checkNotFoundWithId(user, id);
        user.setEnabled(enabled);
        repository.save(user);
    }
}