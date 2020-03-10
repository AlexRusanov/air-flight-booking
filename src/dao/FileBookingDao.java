package dao;

import exceptions.BookingExistsException;
import exceptions.BookingNotFoundException;
import model.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileBookingDao implements BookingDao {
    private final List<Booking> bookingList;

    public FileBookingDao() {
        this.bookingList = loadAllBookingsFromFile().orElse(new ArrayList<>());
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    @Override
    public List<Booking> getAllBookingsByPassenger(String passenger) {
        return bookingList.stream()
                .filter(booking -> booking.getPassengers().contains(passenger))
                .collect(Collectors.toList());
    }

    @Override
    public void saveAllBookingsToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("bookings.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(bookingList);
        objectOutputStream.close();
    }

    @Override
    public Optional<List<Booking>> loadAllBookingsFromFile() {
        List<Booking> bookings = null;

        try (FileInputStream fileInputStream = new FileInputStream("bookings.txt");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            bookings = (List<Booking>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }

        return Optional.ofNullable(bookings);
    }

    @Override
    public Optional<Booking> getBookingById(String id) {
        return bookingList.stream()
                .filter(el -> el.getId().equals(id))
                .findFirst();
    }

    @Override
    public void saveBooking(Booking booking) throws BookingExistsException {
        if (bookingList.contains(booking)) {
            throw new BookingExistsException("Such booking already exists");
        } else {
            bookingList.add(booking);
        }
    }

    @Override
    public void deleteBooking(String id) {
        bookingList.remove(getBookingById(id).orElseThrow(() -> new BookingNotFoundException("There is no booking with id - " + id)));
    }
}
