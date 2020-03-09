package controller;

import exceptions.FlightException;
import model.Flight;
import service.FlightService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public void printAllFlights() {
        Optional<List<Flight>> allFlights = Optional.empty();
        try {
            allFlights = flightService.getAllFlights();

            int sizeFlight = allFlights
                    .map(List::size)
                    .orElse(0);

            Optional<List<Flight>> finalAllFlights = allFlights;
            IntStream.range(0, sizeFlight)
                    .mapToObj(i -> (i + 1) + ". " + finalAllFlights.get().get(i))
                    .forEach(System.out::println);
        } catch (FlightException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printFlightById(String flightId) {
        Flight flight = null;
        try {
            flight = flightService.getFlightById(flightId).get();
            System.out.println(flight);
        } catch (FlightException e) {
            System.out.println(e.getMessage());
        }

    }

    public void printFlightByParams(String from, LocalDateTime departureTime, int qtyFreePlaces) {
        Optional<List<Flight>> flightsByParams = Optional.empty();
        try {
            flightsByParams = flightService.findFlightsByParams(from, departureTime, qtyFreePlaces);

            int sizeFlight = flightsByParams
                    .map(List::size)
                    .orElse(0);
            Optional<List<Flight>> finalFlightsByParams = flightsByParams;
            IntStream.range(0, sizeFlight)
                    .mapToObj(i -> (i + 1) + ". " + finalFlightsByParams.get())
                    .forEach(System.out::println);
        } catch (FlightException e) {
            System.out.println(e.getMessage());
        }
    }
}
