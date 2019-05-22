package ru.igorole.voting.repository;


import ru.igorole.voting.model.User;

public interface UserRepository extends Repository<User> {
    User getByEmail(String email);

    User get(int id);
}