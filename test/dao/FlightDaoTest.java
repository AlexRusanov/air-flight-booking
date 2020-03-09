package dao;

import exceptions.FlightException;
import model.Flight;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

public class FlightDaoTest {

    @Test
    public void getAllFlights_When_IsNotEmpty() throws FlightException {
        //given
        FlightDao flightDAO = new FileFlightDao();
        //when
        Optional<List<Flight>> allFlights = flightDAO.getAllFlights();
        //then
        Assert.assertTrue(allFlights.isPresent());
    }

    @Test
    public void getFlightById_When_ifPresent() throws FlightException {
        //given
        FileFlightDao flightDAO = new FileFlightDao();
        //when
        Flight find = flightDAO.getAllFlights().orElse(new ArrayList<>()).get(1);
        String findId = find.getFlightId();
        //then
        Assert.assertEquals(Optional.of(find), flightDAO.getFlightById(findId));
    }

    @Test
    public void findFlightsByParams_When_ifPresent() throws FlightException {
        //given
        FileFlightDao flightDAO = new FileFlightDao();
        //when
        String findFrom = flightDAO.getAllFlights().orElse(new ArrayList<>()).get(1).getFrom();
        LocalDateTime findDepartureTime = flightDAO.getAllFlights().orElse(new ArrayList<>()).get(1).getDepartureTime();
        int findQtyFreePlaces = flightDAO.getAllFlights().orElse(new ArrayList<>()).get(1).getQtyFreePlaces();
        List<Flight> findList = new ArrayList<>();
        findList.add(flightDAO.getAllFlights().orElse(new ArrayList<>()).get(1));
        //then
        Assert.assertEquals(Optional.of(findList), flightDAO.findFlightsByParams(findFrom, findDepartureTime, findQtyFreePlaces));
    }
}