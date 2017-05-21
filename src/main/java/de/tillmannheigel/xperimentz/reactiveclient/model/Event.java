package de.tillmannheigel.xperimentz.reactiveclient.model;

import java.util.Date;

import lombok.Data;

/**
 * Created by tillmannheigel on 21.05.17.
 */
@Data
public class Event {
    private final long id;
    private final Date date;

}
