package ru.igorole.voting.to;

import ru.igorole.voting.model.Dish;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

public class RestaurantTo extends BaseTo {

    List<Dish> dishes;

    Integer id;

    @NotNull
    Date date;

    @NotBlank
    String name;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, List<Dish> dishes) {
        this.setId(id);
        this.dishes = dishes;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
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
