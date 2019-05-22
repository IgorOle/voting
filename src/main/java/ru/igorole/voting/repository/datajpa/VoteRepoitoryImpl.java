package ru.igorole.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import ru.igorole.voting.model.Vote;
import ru.igorole.voting.repository.VoteRepository;

import java.util.List;

@Cacheable(value = "votes")
public class VoteRepoitoryImpl implements VoteRepository {
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
        return repository.findAll(SORT_DATE);
    }

}

