package model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class Flight implements Serializable {
    private String idFlight;
    private String from;
    private String to;
    private int qtyFreePlaces;
    private long departureTime;

    public Flight(String from, String to, int qtyFreePlaces, long departureTime) {
        this.idFlight = UUID.randomUUID().toString();
        this.from = from;
        this.to = to;
        this.qtyFreePlaces = qtyFreePlaces;
        this.departureTime = departureTime;
    }

    public String getIdFlight() {
        return idFlight;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getQtyFreePlaces() {
        return qtyFreePlaces;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void bookingFlight() {
        qtyFreePlaces++;
    }

    public void cancelBookingFlight() {
        qtyFreePlaces--;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "idFlight='" + idFlight + '\'' +
                "from='" + from + '\'' +
                "to='" + to + '\'' +
                "qtyFreePlaces='" + qtyFreePlaces + '\'' +
                "departureTime='" + LocalDateTime.ofInstant(Instant.ofEpochMilli(departureTime), ZoneId.systemDefault()) + '\'' +
                '}';
    }
}
