package dao;

import model.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileBookingDao implements BookingDao{
    private final List<Booking> bookingList;

    public FileBookingDao() throws IOException, ClassNotFoundException {
        this.bookingList = loadAllBookingsFromFile().orElse(new ArrayList<>());
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
    }

    @Override
    public Optional<List<Booking>> loadAllBookingsFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("bookings.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        return Optional.of((List<Booking>) objectInputStream.readObject());
    }

    @Override
    public Optional<Booking> getBookingById(String id) {
        return bookingList.stream()
                .filter(el -> el.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean saveBooking(Booking booking) {
        return !bookingList.contains(booking) && bookingList.add(booking);
    }

    @Override
    public boolean deleteBooking(String id) {
        return bookingList.remove(getBookingById(id).orElse(null));
    }
}
