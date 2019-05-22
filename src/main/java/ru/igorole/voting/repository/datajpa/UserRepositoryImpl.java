package ru.igorole.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ru.igorole.voting.model.User;
import ru.igorole.voting.repository.UserRepository;

import java.util.List;

@Cacheable(value = "users")
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private CrudUserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User save(User user) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

}