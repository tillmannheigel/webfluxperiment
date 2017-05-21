package de.tillmannheigel.xperimentz.reactiveclient.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.tillmannheigel.xperimentz.reactiveclient.model.Event;

import reactor.core.publisher.Mono;

/**
 * Created by tillmannheigel on 21.05.17.
 */
@RestController
public class BasicController {

    @GetMapping("/event/{id}")
    Mono<Event> eventById(@PathVariable long id){
        return Mono.just(new Event(id, new Date()));
    }

}
