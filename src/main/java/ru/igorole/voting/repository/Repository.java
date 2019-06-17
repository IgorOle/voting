package ru.igorole.voting.repository;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface Repository<T> {
    Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");
    Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");
    Sort SORT_DATETIME = new Sort(Sort.Direction.DESC, "dateTime");
    Sort SORT_DATE_RESTAURANT = new Sort(Sort.Direction.DESC, "date", "restaurant");

    boolean delete(int id);

    T get(int id);

    T save(T obj);

    List<T> getAll();
}
