package ru.igorole.voting.to;

import ru.igorole.voting.model.Dish;

import java.util.List;

public class MenuTo extends BaseTo {

//    @Column(name = "date_is", nullable = false)
//    @NotNull
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate date;

//    Restaurant restaurant;

    List<Dish> dishes;

    public MenuTo(List<Dish> dishes) {
//        this.date = date;
        this.dishes = dishes;
//        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }

//    public Restaurant getRestaurant() {
//        return restaurant;
//    }
//
//    public void setRestaurant(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }
}
