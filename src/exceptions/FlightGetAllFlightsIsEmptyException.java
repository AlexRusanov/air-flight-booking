package exceptions;

public class FlightGetAllFlightsIsEmptyException extends RuntimeException {
    public FlightGetAllFlightsIsEmptyException(String message) {
        super(message);
    }
}
