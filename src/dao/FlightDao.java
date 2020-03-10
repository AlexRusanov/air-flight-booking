package dao;

import model.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightDao {
    Optional<List<Flight>> getAllFlights();

    Optional<List<Flight>> loadAllFlight();

    Optional<Flight> getFlightById(String flightId);

    void saveFlights();

    void bookingFlight(String flightId);

    void cancelBookingFlight(String flightId);

    Optional<List<Flight>> findFlightsByParams(String from, LocalDate departureTime, int qtyFreePlaces);
}
