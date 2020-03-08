package exceptions;

public class BookingExistsException extends Throwable {
    public BookingExistsException(String message) {
        super(message);
    }
}
