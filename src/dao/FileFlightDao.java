package dao;

import exceptions.FlightLoadAllFlightException;
import exceptions.FlightSaveFlightsException;
import model.Flight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileFlightDao implements FlightDao {
    private final List<Flight> flightList;

    public FileFlightDao() {
        this.flightList = loadAllFlight().orElse(new ArrayList<>());
    }

    @Override
    public Optional<List<Flight>> getAllFlights() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);

        return Optional.of(flightList.stream()
                .filter(e -> (e.getDepartureTime().isAfter(now) || e.getDepartureTime().isEqual(now))
                        && (e.getDepartureTime().isBefore(tomorrow) || e.getDepartureTime().isEqual(tomorrow)))
                .collect(Collectors.toList()));
    }

    @Override
    public void saveFlights() throws FlightSaveFlightsException {
        try (FileOutputStream fileOutputStream = new FileOutputStream("flights.txt");) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(flightList);
        } catch (IOException e) {
            throw new FlightSaveFlightsException("loadAllFlight: " + e.getMessage());
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
        return flightList.stream()
                .filter(i -> i.getFlightId().equals(flightId))
                .findFirst();
    }

    @Override
    public Optional<List<Flight>> findFlightsByParams(String from, LocalDate departureTime, int qtyFreePlaces) {
        List<Flight> collect = flightList.stream()
                .filter(i -> i.getFrom().toLowerCase().equals(from.toLowerCase()) &&
                        i.getQtyFreePlaces() >= qtyFreePlaces &&
                        i.getDepartureTime().toLocalDate().isEqual(departureTime))
                .collect(Collectors.toList());

        return collect.size() > 0 ? Optional.of(collect) : Optional.empty();
    }
}
