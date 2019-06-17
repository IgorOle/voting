package ru.igorole.voting.to;

import ru.igorole.voting.model.Dish;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

public class RestaurantTo {

    List<Dish> dishes;

    @NotNull
    Date date;

    @NotBlank
    String name;

    List<VoteTo> votes;

    MenuTo menuTo;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, MenuTo menuTo, List<VoteTo> votes) {
        this.name = name;
        this.votes = votes;
        this.menuTo = menuTo;
    }

    public List<VoteTo> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteTo> votes) {
        this.votes = votes;
    }

    public MenuTo getMenuTo() {
        return menuTo;
    }

    public void setMenuTo(MenuTo menuTo) {
        this.menuTo = menuTo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
