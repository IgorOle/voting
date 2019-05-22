package ru.igorole.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import ru.igorole.voting.model.Restaurant;
import ru.igorole.voting.repository.RestaurantRepository;

import java.util.List;

@Cacheable(value = "restaurants")
@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    CrudRestaurantRepository repository;

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll(SORT_NAME);
    }
}
