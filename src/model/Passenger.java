package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Passenger implements Serializable{
    private UUID id;
    private String firstName;
    private String lastName;
    private List<UUID> bookingList;

    public Passenger(String firstName, String lastName) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<UUID> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<UUID> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(id, passenger.id) &&
                Objects.equals(firstName, passenger.firstName) &&
                Objects.equals(lastName, passenger.lastName) &&
                Objects.equals(bookingList, passenger.bookingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, bookingList);
    }

    @Override
    public String toString() {
        return firstName + lastName;
    }
}
