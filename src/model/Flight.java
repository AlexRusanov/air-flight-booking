package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Flight implements Serializable {
    private String flightId;
    private String from;
    private String to;
    private int qtyFreePlaces;
    private LocalDateTime departureTime;

    public Flight(String from, String to, int qtyFreePlaces, LocalDateTime departureTime) {
        this.flightId = UUID.randomUUID().toString();
        this.from = from;
        this.to = to;
        this.qtyFreePlaces = qtyFreePlaces;
        this.departureTime = departureTime;
    }

    public String getFlightId() {
        return flightId;
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void bookingFlight() {
        qtyFreePlaces--;
    }

    public void cancelBookingFlight() {
        qtyFreePlaces++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(flightId, flight.flightId) &&
                Objects.equals(from, flight.from) &&
                Objects.equals(to, flight.to) &&
                Objects.equals(departureTime, flight.departureTime) &&
                qtyFreePlaces == flight.qtyFreePlaces;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, from, to, qtyFreePlaces, departureTime);
    }


    @Override
    public String toString() {
        return "Flight{" +
                "flightId='" + flightId + '\'' +
                "from='" + from + '\'' +
                "to='" + to + '\'' +
                "qtyFreePlaces='" + qtyFreePlaces + '\'' +
                "departureTime='" + departureTime + '\'' +
                '}';
    }
}
