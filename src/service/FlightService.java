package service;

import dao.FlightDao;
import exceptions.FlightGetFlightByIdNotFoundException;
import exceptions.FlightFindFlightsByParamsNotFoundException;
import exceptions.FlightGetAllFlightsIsEmptyException;
import model.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightService {
    private final FlightDao flightDAO;

    public FlightService(FlightDao flightDAO) {
        this.flightDAO = flightDAO;
    }

    public Optional<List<Flight>> getAllFlights() throws FlightGetAllFlightsIsEmptyException {

        Optional<List<Flight>> allFlights = flightDAO.getAllFlights();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);

        if (allFlights.isEmpty()) {
            throw new FlightGetAllFlightsIsEmptyException("Sorry... Flight is empty");
        }

        //get last 24 hours
//        return Optional.of(allFlights.get().stream()
//                .filter(e -> (e.getDepartureTime().isAfter(now) || e.getDepartureTime().isEqual(now))
//                        && (e.getDepartureTime().isBefore(tomorrow) || e.getDepartureTime().isEqual(tomorrow)))
//                .collect(Collectors.toList()));

        //get all
        return allFlights;
    }

    public Optional<Flight> getFlightById(String flightId) throws FlightGetFlightByIdNotFoundException {
        Optional<Flight> flightById = flightDAO.getFlightById(flightId);

        if (flightById.isEmpty()) {
            throw new FlightGetFlightByIdNotFoundException("Sorry... Flight not found by " + flightId);
        }

        return flightById;
    }

    public Optional<List<Flight>> findFlightsByParams(String to, LocalDate departureTime, int qtyFreePlaces) throws FlightFindFlightsByParamsNotFoundException {
        Optional<List<Flight>> findFlightsByParams = flightDAO.findFlightsByParams(to, departureTime, qtyFreePlaces);

        if (findFlightsByParams.isEmpty()) {
            throw new FlightFindFlightsByParamsNotFoundException("Sorry... Flight not found by params ( " +
                    to + ", " +
                    departureTime + ", " +
                    qtyFreePlaces + " )");
        }

        return findFlightsByParams;
    }
}
