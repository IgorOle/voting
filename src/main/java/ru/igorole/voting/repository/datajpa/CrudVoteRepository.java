package ru.igorole.voting.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.igorole.voting.model.Restaurant;
import ru.igorole.voting.model.User;
import ru.igorole.voting.model.Vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Vote save(Vote vote);

    @Override
    Optional<Vote> findById(Integer id);

    @Override
    List<Vote> findAll(Sort sort);

    List<Vote> findAllByUser(User user);

    List<Vote> findAllByUserAndDateTimeBetween(User user, LocalDateTime start, LocalDateTime end);

    List<Vote> findAllByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Vote> findAllByRestaurantAndDateTimeBetween(Restaurant restaurant, LocalDateTime start, LocalDateTime end);

}
