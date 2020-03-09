package controller;

import dao.FileFlightDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.FlightService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlightControllerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void printAllFlights_When_FlightsIsPrint() {
        //given
        FileFlightDao flightDAO = new FileFlightDao();
        FlightService flightService = new FlightService(flightDAO);
        FlightController flightController = new FlightController(flightService);
        //when
        flightController.printAllFlights();
        //then
        Assert.assertEquals("1. Flight{flightId='eba0bf3d-283f-4a65-9652-04240911fc7e'from='Kyiv'to='Boston'qtyFreePlaces='4'departureTime='2020-03-10T12:54'}\r\n",
                outContent.toString());
    }

    @Test
    public void printFlightById_When_FlightIsPrint() {
        //given
        FileFlightDao flightDAO = new FileFlightDao();
        FlightService flightService = new FlightService(flightDAO);
        FlightController flightController = new FlightController(flightService);
        //when
        String flightId = flightDAO.getAllFlights().orElse(new ArrayList<>()).get(0).getFlightId();
        flightController.printFlightById(flightId);
        //then
        Assert.assertEquals("Flight{flightId='eba0bf3d-283f-4a65-9652-04240911fc7e'from='Kyiv'to='Boston'qtyFreePlaces='4'departureTime='2020-03-10T12:54'}\r\n",
                outContent.toString());
    }

    @Test
    public void printFlightByParams_When_FlightIsPrint() {
        //given
        FileFlightDao flightDAO = new FileFlightDao();
        FlightService flightService = new FlightService(flightDAO);
        FlightController flightController = new FlightController(flightService);
        //when
        String findFrom = flightDAO.getAllFlights().orElse(new ArrayList<>()).get(0).getFrom();
        LocalDateTime findDepartureTime = flightDAO.getAllFlights().orElse(new ArrayList<>()).get(0).getDepartureTime();
        int findQtyFreePlaces = flightDAO.getAllFlights().orElse(new ArrayList<>()).get(0).getQtyFreePlaces();
        flightController.printFlightByParams(findFrom, findDepartureTime.toLocalDate(), findQtyFreePlaces);
        //then
        Assert.assertEquals("1. [Flight{flightId='eba0bf3d-283f-4a65-9652-04240911fc7e'from='Kyiv'to='Boston'qtyFreePlaces='4'departureTime='2020-03-10T12:54'}]\r\n",
                outContent.toString());
    }
}