package dao;

import model.Flight;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

public class FlightDaoTest {

    @Test
    public void getAllFlights_When_IsNotEmpty() {
        //given
        FlightDao flightDAO = new FileFlightDao();
        //when
        Optional<List<Flight>> allFlights = flightDAO.getAllFlights();
        //then
        Assert.assertTrue(allFlights.isPresent());
    }

    @Test
    public void getFlightById_When_ifPresent() {
        //given
        FileFlightDao flightDAO = new FileFlightDao();
        //when
        Flight find = flightDAO.getAllFlights().orElse(new ArrayList<>()).get(0);
        String findId = find.getFlightId();
        //then
        Assert.assertEquals(Optional.of(find), flightDAO.getFlightById(findId));
    }

    @Test
    public void findFlightsByParams_When_ifPresent() {
        //given
        FileFlightDao flightDAO = new FileFlightDao();
        //when
        String findTo = "Bern";
        LocalDate findDepartureTime = LocalDate.of(2020, 5, 4);
        int findQtyFreePlaces = 1;
        //then
        Assert.assertEquals(Optional.of(Collections.singletonList(flightDAO.getFlightById("a9bd5b36-2914-4b8d-bad5-89b9ebb0896d").get())),
                flightDAO.findFlightsByParams(findTo, findDepartureTime, findQtyFreePlaces));
    }
}