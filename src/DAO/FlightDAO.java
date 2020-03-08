package DAO;

import model.Flight;
import java.util.List;

public interface FlightDAO {
    public List<Flight> getFlights();

    public Flight getFlightById(int idFlight);

    public List<Flight> findFlightByParams (String to, String departureTime, int qtyFreePlaces);

}
