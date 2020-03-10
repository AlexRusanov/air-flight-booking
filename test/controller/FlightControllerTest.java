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
        Assert.assertEquals("1. Flight{flightId='97518012-4420-4a52-a838-7cbd7eb7e387'from='Kyiv'to='London'qtyFreePlaces='5'departureTime='2020-03-09T13:32'}\r\n" +
                        "2. Flight{flightId='550cca24-2027-4556-91ff-a818e3496c0f'from='Kyiv'to='Tallinn'qtyFreePlaces='3'departureTime='2020-03-09T18:48'}\r\n" +
                        "3. Flight{flightId='eba0bf3d-283f-4a65-9652-04240911fc7e'from='Kyiv'to='Boston'qtyFreePlaces='4'departureTime='2020-03-10T12:54'}\r\n" +
                        "4. Flight{flightId='a9bd5b36-2914-4b8d-bad5-89b9ebb0896d'from='Kyiv'to='Bern'qtyFreePlaces='5'departureTime='2020-05-04T22:11'}\r\n" +
                        "5. Flight{flightId='073d2a91-7776-4771-8cc3-7eda2a6bf3ab'from='Kyiv'to='Lisbon'qtyFreePlaces='1'departureTime='2020-09-23T23:10'}\r\n" +
                        "6. Flight{flightId='2af0bd3d-f313-4058-9912-d7e7050f2dbf'from='Kyiv'to='Riga'qtyFreePlaces='5'departureTime='2020-11-12T13:22'}\r\n" +
                        "7. Flight{flightId='d97d66ff-a55b-4454-b5e6-7e07237c8408'from='Kyiv'to='Dublin'qtyFreePlaces='2'departureTime='2020-07-08T10:23'}\r\n",
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
        Assert.assertEquals("Flight{flightId='97518012-4420-4a52-a838-7cbd7eb7e387'from='Kyiv'to='London'qtyFreePlaces='5'departureTime='2020-03-09T13:32'}\r\n",
                outContent.toString());
    }

    @Test
    public void printFlightByParams_When_FlightIsPrint() {
        //given
        FileFlightDao flightDAO = new FileFlightDao();
        FlightService flightService = new FlightService(flightDAO);
        FlightController flightController = new FlightController(flightService);
        //when
        String findTo = flightDAO.getAllFlights().orElse(new ArrayList<>()).get(0).getTo();
        LocalDateTime findDepartureTime = flightDAO.getAllFlights().orElse(new ArrayList<>()).get(0).getDepartureTime();
        int findQtyFreePlaces = flightDAO.getAllFlights().orElse(new ArrayList<>()).get(0).getQtyFreePlaces();
        flightController.printFlightByParams(findTo, findDepartureTime.toLocalDate(), findQtyFreePlaces);
        //then
        Assert.assertEquals("1. [Flight{flightId='97518012-4420-4a52-a838-7cbd7eb7e387'from='Kyiv'to='London'qtyFreePlaces='5'departureTime='2020-03-09T13:32'}]\r\n",
                outContent.toString());
    }
}