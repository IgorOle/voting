package ru.igorole.voting.util;

import ru.igorole.voting.model.Vote;
import ru.igorole.voting.to.VoteTo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

public class VoteUtil {
    public static List<Vote> findLastVotes(List<Vote> votes) {
        Comparator<Vote> dt = Comparator.comparing(Vote::getDateTime);
        return votes.stream().collect(groupingBy(Vote::getUser, maxBy(dt)))
                .values().stream()
                .map(vote -> vote.orElse(null)).collect(Collectors.toList());
    }

    public static VoteTo asTO(Vote vote) {
        return new VoteTo(vote.getDateTime(), vote.getUser());
    }

    public static List<VoteTo> asTO(List<Vote> votes) {
        return votes.stream().map(v -> (asTO(v))).collect(Collectors.toList());
    }
}


