package controller;

import exceptions.BookingExistsException;
import exceptions.BookingNotFoundException;
import service.BookingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void printAllBookingsByPassenger(String passenger) {
        try {
            IntStream.range(0, bookingService.getAllBookingsByPassenger(passenger).size())
                    .mapToObj(i -> (i + 1) + ". " + bookingService.getAllBookingsByPassenger(passenger).get(i))
                    .forEach(System.out::println);
        } catch (BookingNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createBooking(String destination, LocalDateTime date, String flightId, List<String> passengers) {
        try {
            bookingService.createBooking(destination, date, flightId, passengers);
            System.out.println("Your booking is confirmed. Thank you for choosing our service!");
        } catch (BookingExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBooking(String id) {
        try {
            bookingService.deleteBooking(id);
            System.out.println("Your booking " + id + " is deleted");
        } catch (BookingNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveAllBookingsToFile() {
        bookingService.saveAllBookingsToFile();
    }
}
