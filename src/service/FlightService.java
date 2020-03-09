package service;

import dao.FlightDao;
import exceptions.FlightException;
import model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class FlightService {
    private final FlightDao flightDAO;

    public FlightService(FlightDao flightDAO) {
        this.flightDAO = flightDAO;
    }

    public Optional<List<Flight>> getAllFlights() throws FlightException {
        Optional<List<Flight>> allFlights = flightDAO.getAllFlights();

        if (allFlights.isEmpty()) {
            throw new FlightException("Sorry... Flight is empty");
        }

        return allFlights;
    }

    public Optional<Flight> getFlightById(String flightId) throws FlightException {
        Optional<Flight> flightById = flightDAO.getFlightById(flightId);

        if (flightById.isEmpty()) {
            throw new FlightException("Sorry... Flight not found by " + flightId);
        }

        return flightById;
    }

    public Optional<List<Flight>> findFlightsByParams(String from, LocalDateTime departureTime, int qtyFreePlaces) throws FlightException {
        Optional<List<Flight>> findFlightsByParams = flightDAO.findFlightsByParams(from, departureTime, qtyFreePlaces);

        if (findFlightsByParams.isEmpty()) {
            throw new FlightException("Sorry... Flight not found by params ( " +
                    from + ", " +
                    departureTime + ", " +
                    qtyFreePlaces + " )");
        }

        return findFlightsByParams;
    }
}
