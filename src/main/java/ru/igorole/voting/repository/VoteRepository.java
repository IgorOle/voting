package ru.igorole.voting.repository;

import ru.igorole.voting.model.Restaurant;
import ru.igorole.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository extends Repository<Vote> {

    List<Vote> findAllByDateJournal(LocalDate date);

    List<Vote> findAllByDate(LocalDate date);

    List<Vote> findAllByRestaurantAndDate(Restaurant restaurant, LocalDate date);
}
