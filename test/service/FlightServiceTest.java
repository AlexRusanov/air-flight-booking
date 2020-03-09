package service;

import dao.FileFlightDao;
import exceptions.FlightException;

import model.Flight;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class FlightServiceTest {

    @Test(expected = FlightException.class)
    public void getAllFlights_When_HasException_IsEmpty() throws FlightException {
        //given
        FileFlightDao flightDAO = new FileFlightDao() {
            @Override
            public Optional<List<Flight>> getAllFlights() {
                return Optional.empty();
            }
        };
        FlightService flightService = new FlightService(flightDAO);
        //when
        flightService.getAllFlights();
        //then
    }

    @Test(expected = FlightException.class)
    public void getFlightById_When_HasException_NotFound() throws FlightException {
        //given
        FileFlightDao flightDAO = new FileFlightDao();
        FlightService flightService = new FlightService(flightDAO);
        //when
        flightService.getFlightById("asdasdasd");
        //then
    }

    @Test(expected = FlightException.class)
    public void findFlightsByParams_When_HasException_NotFound() throws FlightException {
        //given
        FileFlightDao flightDAO = new FileFlightDao();
        FlightService flightService = new FlightService(flightDAO);
        //when
        Optional<List<Flight>> flightsByParams = flightService.findFlightsByParams("Dnepr",
                LocalDateTime.of(2020, 3, 10, 12, 54),
                1);
        //then
    }
}