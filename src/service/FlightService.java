package service;

import dao.FlightDao;
import exceptions.FlightGetFlightByIdNotFoundException;
import exceptions.FlightFindFlightsByParamsNotFoundException;
import exceptions.FlightGetAllFlightsIsEmptyException;
import model.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FlightService {
    private final FlightDao flightDAO;

    public FlightService(FlightDao flightDAO) {
        this.flightDAO = flightDAO;
    }

    public Optional<List<Flight>> getAllFlights() throws FlightGetAllFlightsIsEmptyException {
        Optional<List<Flight>> allFlights = flightDAO.getAllFlights();

        if (allFlights.isEmpty()) {
            throw new FlightGetAllFlightsIsEmptyException("Sorry... Flight is empty");
        }

        return allFlights;
    }

    public Optional<Flight> getFlightById(String flightId) throws FlightGetFlightByIdNotFoundException {
        Optional<Flight> flightById = flightDAO.getFlightById(flightId);

        if (flightById.isEmpty()) {
            throw new FlightGetFlightByIdNotFoundException("Sorry... Flight not found by " + flightId);
        }

        return flightById;
    }

    public Optional<List<Flight>> findFlightsByParams(String from, LocalDate departureTime, int qtyFreePlaces) throws FlightFindFlightsByParamsNotFoundException {
        Optional<List<Flight>> findFlightsByParams = flightDAO.findFlightsByParams(from, departureTime, qtyFreePlaces);

        if (findFlightsByParams.isEmpty()) {
            throw new FlightFindFlightsByParamsNotFoundException("Sorry... Flight not found by params ( " +
                    from + ", " +
                    departureTime + ", " +
                    qtyFreePlaces + " )");
        }

        return findFlightsByParams;
    }
}
