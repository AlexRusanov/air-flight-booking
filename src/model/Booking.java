package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Booking implements Serializable {
    private String id;
    private String destination;
    private LocalDateTime date;
    private int passengersCount;
    private int flightId;
    private List<String> passengers;

    public Booking(String destination, LocalDateTime date, int passengersCount, int flightId, List<String> passengers) {
        this.id = UUID.randomUUID().toString();
        this.destination = destination;
        this.date = date;
        this.passengersCount = passengersCount;
        this.flightId = flightId;
        this.passengers = passengers;
    }

    public String getId() {
        return id;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return passengersCount == booking.passengersCount &&
                flightId == booking.flightId &&
                Objects.equals(destination, booking.destination) &&
                Objects.equals(date, booking.date) &&
                Objects.equals(passengers, booking.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, date, passengersCount, flightId, passengers);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                ", passangersCount=" + passengersCount +
                ", flightId=" + flightId +
                ", passangers=" + passengers +
                '}';
    }
}
