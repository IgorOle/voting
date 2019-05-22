package ru.igorole.voting.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.igorole.voting.repository.datajpa.CrudVoteRepository;
import ru.igorole.voting.to.VoteTo;
import ru.igorole.voting.util.VoteUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(AdminVoteRestController.REST_URL)
public class AdminVoteRestController {
    static final String REST_URL = "/rest/admin/votes";

    @Autowired
    CrudVoteRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VoteTo> getAll(@RequestParam(value = "date", required = false) LocalDate date) {
        if (date == null) {
            return VoteUtil.fromVoteToTo(repository.findAll());
        } else {
            return VoteUtil.fromVoteToTo(
                    repository.findAllByDateTimeBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX)));
        }
    }

}
