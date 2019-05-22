package ru.igorole.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.time.LocalTime;


@Configuration
@PropertySource("classpath:ini.properties")
public class Properties {

    @Autowired
    Environment environment;

    public LocalTime getMaxTimeForVoting() {
        //how add converter?
        //return environment.getProperty("maxTimeForVoting", LocalTime.class, LocalTime.parse("10:00:00"));
        return LocalTime.parse(environment.getProperty("maxTimeForVoting", "10:00:00"));
    }
}
