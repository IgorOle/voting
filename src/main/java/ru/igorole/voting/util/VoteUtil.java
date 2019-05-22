package ru.igorole.voting.util;

import ru.igorole.voting.model.Vote;
import ru.igorole.voting.to.VoteTo;

import java.util.List;
import java.util.stream.Collectors;

public class VoteUtil {
    public static List<VoteTo> fromVoteToTo(List<Vote> votes) {
        return votes.stream().map(vote -> (fromVoteToTo(vote))).collect(Collectors.toList());
    }

    public static VoteTo fromVoteToTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getDateTime(), vote.getRestaurant());
    }
}
