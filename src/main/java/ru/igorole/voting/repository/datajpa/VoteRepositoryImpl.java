package ru.igorole.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import ru.igorole.voting.model.Restaurant;
import ru.igorole.voting.model.Vote;
import ru.igorole.voting.repository.VoteRepository;
import ru.igorole.voting.util.VoteUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Cacheable(value = "votes")
@Repository
public class VoteRepositoryImpl implements VoteRepository {
    @Autowired
    CrudVoteRepository repository;

    @CacheEvict(value = "votes", allEntries = true)
    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Vote get(int id) {
        return repository.getOne(id);
    }

    @CacheEvict(value = "votes", allEntries = true)
    @Override
    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public List<Vote> getAll() {
        return repository.findAll(SORT_DATETIME);
    }

    //all votes
    @Override
    public List<Vote> findAllByDateJournal(LocalDate date) {
        return repository.findAllByDateTimeBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    public List<Vote> findAllByRestaurantAndDate(Restaurant restaurant, LocalDate date) {
        return VoteUtil.findLastVotes(repository.findAllByRestaurantAndDateTimeBetween(restaurant, date.atStartOfDay(), date.atTime(LocalTime.MAX)));
    }

    //get only last votes for counting
    public List<Vote> findAllByDate(LocalDate date) {
        return VoteUtil.findLastVotes(findAllByDateJournal(date));
    }


}

