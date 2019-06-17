package ru.igorole.voting.web.vote;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.igorole.voting.model.Vote;
import ru.igorole.voting.repository.VoteRepository;
import ru.igorole.voting.web.AbstractController;

@RestController
@RequestMapping(AdminVoteRestController.REST_URL)
public class AdminVoteRestController extends AbstractController<VoteRepository, Vote> {
    static final String REST_URL = "/rest/admin/votes";

    public AdminVoteRestController() {
        super(REST_URL);
    }

}
