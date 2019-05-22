package ru.igorole.voting.to;

import ru.igorole.voting.model.Restaurant;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class VoteTo extends BaseTo {

    @NotNull
    LocalDateTime dateTime;

    @NotNull
    Restaurant restaurant;

    public VoteTo(@NotNull LocalDateTime dateTime, Restaurant restaurant) {
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public VoteTo(Integer id, LocalDateTime dateTime, Restaurant restaurant) {
        this.id = id;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
