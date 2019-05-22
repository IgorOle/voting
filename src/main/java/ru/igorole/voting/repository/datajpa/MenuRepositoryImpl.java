package ru.igorole.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import ru.igorole.voting.model.Menu;
import ru.igorole.voting.repository.MenuRepository;

import java.util.List;

@Cacheable(value = "menu")
@Repository
public class MenuRepositoryImpl implements MenuRepository {

    @Autowired
    CrudMenuRepository repository;

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public Menu save(Menu menu) {
        return repository.save(menu);
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Menu get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Menu> getAll() {
        return repository.findAll(SORT_DATE_RESTAURANT);
    }
}
