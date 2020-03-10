package service;

import dao.FileBookingDao;
import dao.FileFlightDao;
import exceptions.BookingExistsException;
import exceptions.BookingNotFoundException;
import model.Booking;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class BookingServiceTest {
    @Test(expected = BookingNotFoundException.class)
    public void getAllBookingsByPassengerThrowsBookingNotFoundExceptionWhenThereIsNoBookingsForPassenger() throws BookingExistsException {
        //given
        FileBookingDao bookingDao = new FileBookingDao();
        FileFlightDao flightDao = new FileFlightDao();
        BookingService bookingService = new BookingService(bookingDao, flightDao);
        //when
        Booking smith1 = new Booking("London", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("John Smith", "James Black", "Elithabet Taylor"));
        Booking smith2 = new Booking("Paris", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("John Smith", "Elithabet Taylor"));
        bookingDao.saveBooking(smith1);
        bookingDao.saveBooking(smith2);
        bookingDao.saveBooking(new Booking("Rome", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("James Black", "Elithabet Taylor")));
        String passenger = "Tom Jerry";
        //then
        bookingService.getAllBookingsByPassenger(passenger);
    }

    @Test(expected = BookingExistsException.class)
    public void createBookingThrowsBookingExistsExceptionWhenGivenBookingAlreadyExists() throws BookingExistsException {
        //given
        FileBookingDao bookingDao = new FileBookingDao();
        FileFlightDao flightDao = new FileFlightDao();
        BookingService bookingService = new BookingService(bookingDao, flightDao);
        //when
        Booking smith1 = new Booking("London", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("John Smith", "James Black", "Elithabet Taylor"));
        Booking smith2 = new Booking("Paris", LocalDateTime.of(2020, 5, 4, 13, 13), "Paris_2020_5_4_13_13", Arrays.asList("John Smith", "Elithabet Taylor"));
        bookingDao.saveBooking(smith1);
        bookingDao.saveBooking(smith2);
        //then
        bookingService.createBooking("Paris", LocalDateTime.of(2020, 5, 4, 13, 13), "Paris_2020_5_4_13_13", Arrays.asList("John Smith", "Elithabet Taylor"));
    }

    @Test(expected = BookingNotFoundException.class)
    public void deleteBookingThrowsBookingNotFoundExceptionWhenThereIsNoBookingsWithGivenId() throws BookingExistsException {
        //given
        FileBookingDao bookingDao = new FileBookingDao();
        FileFlightDao flightDao = new FileFlightDao();
        BookingService bookingService = new BookingService(bookingDao, flightDao);
        //when
        Booking smith1 = new Booking("London", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("John Smith", "James Black", "Elithabet Taylor"));
        Booking smith2 = new Booking("Paris", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("John Smith", "Elithabet Taylor"));
        bookingDao.saveBooking(smith1);
        bookingDao.saveBooking(smith2);
        bookingDao.saveBooking(new Booking("Rome", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("James Black", "Elithabet Taylor")));
        String passenger = "Tom Jerry";
        //then
        bookingService.deleteBooking(UUID.randomUUID().toString());
    }
}