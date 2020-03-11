package dao;

import exceptions.FlightLoadAllFlightException;
import exceptions.FlightSaveFlightsException;
import model.Flight;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileFlightDao implements FlightDao {
    private Optional<List<Flight>> flightList;

    public FileFlightDao() {
        flightList = loadAllFlight();
    }

    @Override
    public Optional<List<Flight>> getAllFlights() {
        return flightList;
    }

    @Override
    public void saveFlights() throws FlightSaveFlightsException {
        try (FileOutputStream fileOutputStream = new FileOutputStream("flights.txt");) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(flightList.get());
        } catch (IOException e) {
            throw new FlightSaveFlightsException("saveFlights: " + e.getMessage());
        }
    }

    @Override
    public void bookingFlight(String flightId) {
        for (Flight flight : flightList.get()) {
            if (flight.getFlightId().equals(flightId)) {
                flight.bookingFlight();
            }
        }
    }

    @Override
    public void cancelBookingFlight(String flightId) {
        for (Flight flight : flightList.get()) {
            if (flight.getFlightId().equals(flightId)) {
                flight.cancelBookingFlight();
            }
        }
    }

    @Override
    public Optional<List<Flight>> loadAllFlight() throws FlightLoadAllFlightException {
        List<Flight> flights;

        try (FileInputStream fileInputStream = new FileInputStream("flights.txt");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            flights = (List<Flight>) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new FlightLoadAllFlightException("loadAllFlight: " + e.getMessage());
        }

        return Optional.ofNullable(flights);
    }

    @Override
    public Optional<Flight> getFlightById(String flightId) {
        return flightList.get().stream()
                .filter(i -> i.getFlightId().equals(flightId))
                .findFirst();
    }

    @Override
    public Optional<List<Flight>> findFlightsByParams(String to, LocalDate departureTime, int qtyFreePlaces) {
        List<Flight> collect = flightList.get().stream()
                .filter(i -> i.getTo().toLowerCase().equals(to.toLowerCase()) &&
                        i.getQtyFreePlaces() >= qtyFreePlaces &&
                        i.getDepartureTime().toLocalDate().isEqual(departureTime))
                .collect(Collectors.toList());

        return collect.size() > 0 ? Optional.of(collect) : Optional.empty();
    }
}
