package ru.igorole.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import ru.igorole.voting.model.Dish;
import ru.igorole.voting.repository.DishRepository;

import java.util.List;

@Cacheable(value = "dishes")
@Repository
public class DishRepositoryImpl implements DishRepository {

    @Autowired
    CrudDishRepository repository;

    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getAll() {
        return repository.findAll(SORT_NAME);
    }
}
