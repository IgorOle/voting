package ru.igorole.voting.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.igorole.voting.model.User;
import ru.igorole.voting.repository.UserRepository;
import ru.igorole.voting.to.UserTo;
import ru.igorole.voting.util.UserUtil;
import ru.igorole.voting.web.SecurityUtil;

import javax.validation.Valid;
import java.net.URI;

import static ru.igorole.voting.util.ValidationUtil.assureIdConsistent;
import static ru.igorole.voting.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(ProfileUserRestController.REST_URL)
public class ProfileUserRestController {
    static final String REST_URL = "/rest/profile";

    @Autowired
    UserRepository userRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        int id = SecurityUtil.authUserId();
        User user = userRepository.get(id);
        checkNotFoundWithId(user, id);
        return user;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserTo userTo) {
        assureIdConsistent(userTo, SecurityUtil.authUserId());
        User user = userRepository.get(SecurityUtil.authUserId());
        UserUtil.updateFromTo(user, userTo);
        userRepository.save(user);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        User created = userRepository.save(UserUtil.prepareToSave(UserUtil.createNewFromTo(userTo)));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
