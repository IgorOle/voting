package ru.igorole.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.igorole.voting.model.Menu;
import ru.igorole.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Menu save(Menu menu);

    @Override
    Optional<Menu> findById(Integer id);

    List<Menu> findAllByRestaurantAndDate(Restaurant restaurant, LocalDate date);

    List<Menu> findAllByDate(LocalDate date);
}

