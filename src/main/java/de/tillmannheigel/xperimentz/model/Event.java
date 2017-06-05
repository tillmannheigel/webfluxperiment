package de.tillmannheigel.xperimentz.model;

import lombok.Value;

import java.util.Date;

/**
 * Created by tillmannheigel on 21.05.17.
 */

@Value
public class Event {
    long id;
    Date date;
}
