package service;

import dao.BookingDao;
import dao.FlightDao;
import exceptions.BookingExistsException;
import exceptions.BookingNotFoundException;
import model.Booking;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BookingService {
    private final BookingDao bookingDao;
    private final FlightDao flightDao;

    public BookingService(BookingDao bookingDao, FlightDao flightDao) {
        this.bookingDao = bookingDao;
        this.flightDao = flightDao;
    }

    public List<Booking> getAllBookingsByPassenger(String passenger) {
        List<Booking> bookings = bookingDao.getAllBookingsByPassenger(passenger);

        if (bookings.size() == 0) {
            throw new BookingNotFoundException("There is no bookings for passenger " + passenger);
        }

        return bookingDao.getAllBookingsByPassenger(passenger);
    }

    public void createBooking(String destination, LocalDateTime date, String flightId, List<String> passengers) throws BookingExistsException {
        for (int i = 0; i < passengers.size(); i++) {
            flightDao.bookingFlight(flightId);
        }

        bookingDao.saveBooking(new Booking(destination, date, flightId, passengers));
    }


    public Optional<Booking> getBookingById(String id) {
        return bookingDao.getBookingById(id);
    }

    public void deleteBooking(String id) {
        Booking booking = getBookingById(id).get();
        for (int i = 0; i < booking.getPassengers().size(); i++) {
            flightDao.cancelBookingFlight(booking.getFlightId());
        }

        bookingDao.deleteBooking(id);
    }

    public void saveAllBookingsToFile() {
        try {
            bookingDao.saveAllBookingsToFile();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
