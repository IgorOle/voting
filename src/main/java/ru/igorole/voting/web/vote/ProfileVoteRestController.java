package ru.igorole.voting.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.igorole.voting.model.Vote;
import ru.igorole.voting.repository.datajpa.CrudVoteRepository;
import ru.igorole.voting.service.Properties;
import ru.igorole.voting.web.SecurityUtil;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.igorole.voting.util.ValidationUtil.allowTime;


@RestController
@RequestMapping(ProfileVoteRestController.REST_URL)
public class ProfileVoteRestController {
    static final String REST_URL = "/rest/profile/votes";

    @Autowired
    CrudVoteRepository repository;

    @Autowired
    Properties properties;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll(@RequestParam(value = "date", required = false) LocalDate date) {
        if (date == null) {
            return repository.findAllByUser(SecurityUtil.safeGet().getUser());
        } else {
            return repository.findAllByUserAndDateTimeBetween(SecurityUtil.safeGet().getUser(), date.atStartOfDay(), date.atTime(LocalTime.MAX));
        }
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Vote vote) {
        allowTime(properties.getMaxTimeForVoting());
        vote.setUser(SecurityUtil.safeGet().getUser());
        vote.setDateTime(LocalDateTime.now());
        Vote created = repository.save(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
