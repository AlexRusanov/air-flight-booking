package DAO;

import model.Flight;

import java.io.IOException;
import java.util.List;

public interface FlightDAO {
    public List<Flight> getFlights() throws IOException, ClassNotFoundException;

    public void setFlights(List<Flight> flights) throws IOException;
}
