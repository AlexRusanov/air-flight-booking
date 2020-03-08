package dao;

import model.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileBookingDao implements BookingDao{
    private final List<Booking> bookingList;

    public FileBookingDao(List<Booking> bookingList) {
        this.bookingList = new ArrayList<>(bookingList);
    }

    @Override
    public List<Booking> getAllBookingsByPassengerId(UUID id) {
        return null;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingList;
    }

    @Override
    public Optional<Booking> getBookingById(UUID id) {
        return bookingList.stream().filter(el -> el.getId().equals(id)).findFirst();
    }

    @Override
    public boolean saveBooking(Booking booking) {
        return false;
    }

    @Override
    public boolean updateBooking(UUID id) {
        return false;
    }

    @Override
    public boolean deleteBooking(UUID id) {
        return false;
    }
}
