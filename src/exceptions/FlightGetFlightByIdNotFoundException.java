package exceptions;

public class FlightGetFlightByIdNotFoundException extends RuntimeException {
    public FlightGetFlightByIdNotFoundException(String message) {
        super(message);
    }
}
