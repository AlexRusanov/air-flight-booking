package service;

import dao.FileFlightDao;

import exceptions.FlightFindFlightsByParamsNotFoundException;
import exceptions.FlightGetAllFlightsIsEmptyException;
import exceptions.FlightGetFlightByIdNotFoundException;
import model.Flight;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FlightServiceTest {

    @Test(expected = FlightGetAllFlightsIsEmptyException.class)
    public void getAllFlights_When_HasException_IsEmpty() {
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

    @Test(expected = FlightGetFlightByIdNotFoundException.class)
    public void getFlightById_When_HasException_NotFound() {
        //given
        FileFlightDao flightDAO = new FileFlightDao();
        FlightService flightService = new FlightService(flightDAO);
        //when
        flightService.getFlightById("asdasdasd");
        //then
    }

    @Test(expected = FlightFindFlightsByParamsNotFoundException.class)
    public void findFlightsByParams_When_HasException_NotFound() {
        //given
        FileFlightDao flightDAO = new FileFlightDao();
        FlightService flightService = new FlightService(flightDAO);
        //when
        Optional<List<Flight>> flightsByParams = flightService.findFlightsByParams("Dnepr",
                LocalDate.of(2020, 3, 10),
                1);
        //then
    }
}