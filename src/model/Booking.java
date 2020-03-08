package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Booking implements Serializable {
    private int id;
    private String destination;
    private LocalDateTime date;
    private int passangersCount;
    private int flightId;
    private List<String> passangers;
}
