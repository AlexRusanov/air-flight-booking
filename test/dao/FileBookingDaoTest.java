package dao;

import exceptions.BookingExistsException;
import model.Booking;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

public class FileBookingDaoTest {
    @Test
    public void getAllBookingsByPassengerReturnsBookingsListForPassengerWhenItExists () throws BookingExistsException {
        //given
        BookingDao bookingDao = new FileBookingDao();
        //when
        Booking smith1 = new Booking("London", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("John Smith", "James Black", "Elithabet Taylor"));
        Booking smith2 = new Booking("Paris", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("John Smith", "Elithabet Taylor"));
        bookingDao.saveBooking(smith1);
        bookingDao.saveBooking(smith2);
        bookingDao.saveBooking(new Booking("Rome", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("James Black", "Elithabet Taylor")));
        //then
        Assert.assertEquals(Arrays.asList(smith1, smith2), bookingDao.getAllBookingsByPassenger("John Smith"));
    }

    @Test
    public void saveBookingIncreasesSizeOfBookingListByOneWhenBookingIsNotAlreadyInList () throws BookingExistsException {
        //given
        FileBookingDao bookingDao = new FileBookingDao();
        //when
        int sizeBeforeAdd = bookingDao.getBookingList().size();
        bookingDao.saveBooking(new Booking("London", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("John Smith", "James Black", "Elithabet Taylor")));
        int sizeAfterAdd = bookingDao.getBookingList().size();
        //then
        Assert.assertEquals(sizeBeforeAdd + 1, sizeAfterAdd);
    }

    @Test
    public void getBookingByIdReturnsBookingWhenItExists () throws BookingExistsException {
        //given
        FileBookingDao bookingDao = new FileBookingDao();
        //when
        Booking smith1 = new Booking("London", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("John Smith", "James Black", "Elithabet Taylor"));
        Booking smith2 = new Booking("Paris", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("John Smith", "Elithabet Taylor"));
        bookingDao.saveBooking(smith1);
        bookingDao.saveBooking(smith2);
        String id = bookingDao.getBookingList().get(1).getId();
        //then
        Assert.assertEquals(Optional.of(smith2), bookingDao.getBookingById(id));
    }

    @Test
    public void deleteBookingDecreasesSizeOfBookingListByOneWhenBookingIsPresentInList () throws BookingExistsException {
        //given
        FileBookingDao bookingDao = new FileBookingDao();
        //when
        Booking smith1 = new Booking("London", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("John Smith", "James Black", "Elithabet Taylor"));
        Booking smith2 = new Booking("Paris", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("John Smith", "Elithabet Taylor"));
        bookingDao.saveBooking(smith1);
        bookingDao.saveBooking(smith2);
        bookingDao.saveBooking(new Booking("Rome", LocalDateTime.now(), UUID.randomUUID().toString(), Arrays.asList("James Black", "Elithabet Taylor")));
        String id = bookingDao.getBookingList().get(1).getId();
        int sizeBeforeAdd = bookingDao.getBookingList().size();
        bookingDao.deleteBooking(id);
        int sizeAfterAdd = bookingDao.getBookingList().size();
        //then
        Assert.assertEquals(sizeBeforeAdd - 1, sizeAfterAdd);
    }
}