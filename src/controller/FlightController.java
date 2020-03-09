package controller;

import exceptions.FlightFindFlightsByParamsNotFoundException;
import exceptions.FlightGetAllFlightsIsEmptyException;
import exceptions.FlightGetFlightByIdNotFoundException;
import model.Flight;
import service.FlightService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public void printAllFlights() {
        Optional<List<Flight>> allFlights;
        try {
            allFlights = flightService.getAllFlights();

            int sizeFlight = allFlights
                    .map(List::size)
                    .orElse(0);

            Optional<List<Flight>> finalAllFlights = allFlights;
            IntStream.range(0, sizeFlight)
                    .mapToObj(i -> (i + 1) + ". " + finalAllFlights.get().get(i))
                    .forEach(System.out::println);
        } catch (FlightGetAllFlightsIsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printFlightById(String flightId) {
        Flight flight = null;
        try {
            flight = flightService.getFlightById(flightId).get();
            System.out.println(flight);
        } catch (FlightGetFlightByIdNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }


    public Optional<List<Flight>> printFlightByParams(String to, LocalDate departureTime, int qtyFreePlaces) {
        Optional<List<Flight>> flightsByParams = Optional.empty();
        try {
            flightsByParams = flightService.findFlightsByParams(to, departureTime, qtyFreePlaces);

            int sizeFlight = flightsByParams
                    .map(List::size)
                    .orElse(0);
            Optional<List<Flight>> finalFlightsByParams = flightsByParams;
            IntStream.range(0, sizeFlight)
                    .mapToObj(i -> (i + 1) + ". " + finalFlightsByParams.get())
                    .forEach(System.out::println);
        } catch (FlightFindFlightsByParamsNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return flightsByParams;
    }
}
