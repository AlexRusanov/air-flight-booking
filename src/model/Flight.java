package model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Flight implements Serializable {
    private int idFlight;
    private String from;
    private String to;
    private int qtyFreePlaces;
    private long departureTime;

    public Flight(int idFlight, String from, String to, int qtyFreePlaces, long departureTime) {
        this.idFlight = idFlight;
        this.from = from;
        this.to = to;
        this.qtyFreePlaces = qtyFreePlaces;
        this.departureTime = departureTime;
    }

    public int getIdFlight() {
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
