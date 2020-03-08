package service;

import dao.BookingDao;
import exceptions.BookingExistsException;
import exceptions.BookingNotFoundException;
import model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public class BookingService {
    private final BookingDao bookingDao;

    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    public List<Booking> getAllBookingsByPassenger(String passenger) {
        List<Booking> bookings = bookingDao.getAllBookingsByPassenger(passenger);

        if (bookings.size() == 0) {
            throw new BookingNotFoundException("There is no bookings for passenger " + passenger);
        }

        return bookingDao.getAllBookingsByPassenger(passenger);
    }

    public void createBooking(String destination, LocalDateTime date, String flightId, List<String> passengers) throws BookingExistsException {
        bookingDao.saveBooking(new Booking(destination, date, flightId, passengers));
    }

    public void deleteBooking(String id) {
        bookingDao.deleteBooking(id);
    }
}
