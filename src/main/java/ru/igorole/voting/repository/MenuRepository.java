package ru.igorole.voting.repository;

import ru.igorole.voting.model.Menu;
import ru.igorole.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository extends Repository<Menu> {
    List<Menu> getAllByRestaurantAndDate(Restaurant restaurant, LocalDate date);

    List<Menu> getAllByDate(LocalDate date);
}
