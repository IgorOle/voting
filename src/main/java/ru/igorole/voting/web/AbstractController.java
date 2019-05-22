package ru.igorole.voting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.igorole.voting.HasId;
import ru.igorole.voting.model.User;
import ru.igorole.voting.repository.Repository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.igorole.voting.util.ValidationUtil.checkNotFoundWithId;

public abstract class AbstractController<T extends Repository, A extends HasId> {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    String URL;

    @Autowired
    protected T repository;

    public AbstractController(String URL) {
        this.URL = URL;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<A> getAll() {
        return repository.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public A get(@PathVariable("id") int id) {
        log.info("get {}", id);
        A a = (A) repository.get(id);
        checkNotFoundWithId(a, id);
        return a;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody A object, @PathVariable("id") int id) {
        log.info("update {}", id);
        object.setId(id);
        checkNotFoundWithId(object, id);
        repository.save(object);
    }


    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Valid @RequestBody A object) {
        log.info("create {}", object.toString());
        A created = (A)repository.save(object);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
