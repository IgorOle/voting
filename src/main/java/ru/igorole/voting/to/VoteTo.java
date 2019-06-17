package ru.igorole.voting.to;

import ru.igorole.voting.model.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class VoteTo extends BaseTo {

    @NotNull
    LocalDateTime dateTime;

    User user;


    public VoteTo(LocalDateTime dateTime, User user) {
        this.dateTime = dateTime;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
