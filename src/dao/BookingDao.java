package dao;

import model.Booking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingDao {
    List<Booking> getAllBookingsByPassengerId(UUID id);

    List<Booking> getAllBookings();

    Optional<Booking> getBookingById(UUID id);

    boolean saveBooking(Booking booking);

    boolean updateBooking(UUID id);

    boolean deleteBooking(UUID id);
}
