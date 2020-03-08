package dao;

import exceptions.BookingExistsException;
import model.Booking;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BookingDao {
    List<Booking> getAllBookingsByPassenger(String passenger);

    void saveAllBookingsToFile() throws IOException;

    Optional<List<Booking>> loadAllBookingsFromFile() throws IOException, ClassNotFoundException;

    Optional<Booking> getBookingById(String id);

    void saveBooking(Booking booking) throws BookingExistsException;

    void deleteBooking(String id);
}
