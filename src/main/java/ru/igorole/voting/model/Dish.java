package ru.igorole.voting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "meal_unique_email_idx")})
public class Dish extends AbstractNamedEntity {
    @Column(name = "describe")
    String describe;

    public Dish() {
    }

    public Dish(Integer id, String name, String describe) {
        super(id, name);
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
