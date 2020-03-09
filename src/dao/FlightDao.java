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

    Optional<List<Flight>> findFlightsByParams(String from, LocalDate departureTime, int qtyFreePlaces);
}
