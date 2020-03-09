package dao;

import exceptions.FlightException;
import model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightDao {
    Optional<List<Flight>> getAllFlights();

    Optional<List<Flight>> loadAllFlight() throws FlightException;

    Optional<Flight> getFlightById(String flightId);

    void saveFlights() throws FlightException;

    Optional<List<Flight>> findFlightsByParams(String from, LocalDateTime departureTime, int qtyFreePlaces);
}
