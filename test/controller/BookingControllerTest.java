package controller;

import dao.FileBookingDao;
import dao.FileFlightDao;
import exceptions.BookingExistsException;
import model.Booking;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.BookingService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class BookingControllerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void printAllBookingsByPassengerPrintsBookingsWhenGivenPassengerMadeSome() throws BookingExistsException {
        //given
        FileBookingDao bookingDao = new FileBookingDao();
        FileFlightDao flightDao = new FileFlightDao();
        BookingService bookingService = new BookingService(bookingDao, flightDao);
        BookingController bookingController = new BookingController(bookingService);
        //when
        Booking smith1 = new Booking("London", LocalDateTime.of(2020, 6, 4, 13, 13), "London_2020_6_4_13_13", Arrays.asList("John Smith", "James Black", "Elithabet Taylor"));
        Booking smith2 = new Booking("Paris", LocalDateTime.of(2020, 5, 4, 13, 13), "Paris_2020_5_4_13_13", Arrays.asList("John Smith", "Elithabet Taylor"));
        bookingDao.saveBooking(smith1);
        bookingDao.saveBooking(smith2);
        String id1 = bookingDao.getBookingList().get(0).getId();
        String id2 = bookingDao.getBookingList().get(1).getId();
        bookingDao.saveBooking(new Booking("Rome", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("James Black", "Elithabet Taylor")));
        //then
        bookingController.printAllBookingsByPassenger("John Smith");
        Assert.assertEquals("1. {id=" + id1 + ", destination='London', date=2020-06-04T13:13, flightId=London_2020_6_4_13_13, passangers=[John Smith, James Black, Elithabet Taylor]}\r\n" +
                "2. {id=" + id2 + ", destination='Paris', date=2020-05-04T13:13, flightId=Paris_2020_5_4_13_13, passangers=[John Smith, Elithabet Taylor]}\r\n", outContent.toString());
    }

    @Test
    public void createBookingPrintsConfirmationWhenBookingIsCreated() throws BookingExistsException {
        //given
        FileBookingDao bookingDao = new FileBookingDao();
        FileFlightDao flightDao = new FileFlightDao();
        BookingService bookingService = new BookingService(bookingDao, flightDao);
        BookingController bookingController = new BookingController(bookingService);
        //when
        //then
        bookingController.createBooking("London", LocalDateTime.of(2020, 6, 4, 13, 13), "London_2020_6_4_13_13", Arrays.asList("John Smith", "James Black", "Elithabet Taylor"));
        Assert.assertEquals("Your booking is confirmed. Thank you for choosing our service!\r\n", outContent.toString());
    }

    @Test
    public void deleteBookingPrintsConfirmationOfDeletingWhenBookingIsDeleted() throws BookingExistsException {
        //given
        FileBookingDao bookingDao = new FileBookingDao();
        FileFlightDao flightDao = new FileFlightDao();
        BookingService bookingService = new BookingService(bookingDao, flightDao);
        BookingController bookingController = new BookingController(bookingService);
        //when
        //then
        bookingService.createBooking("London", LocalDateTime.of(2020, 6, 4, 13, 13), "London_2020_6_4_13_13", Arrays.asList("John Smith", "James Black", "Elithabet Taylor"));
        String id = bookingDao.getBookingList().get(0).getId();
        bookingController.deleteBooking(id);
        Assert.assertEquals("Your booking " + id + " is deleted\r\n", outContent.toString());
    }
}