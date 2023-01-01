package joinweb.join.exception;

public class NoMoreBookingAllowedException extends RuntimeException {
    public NoMoreBookingAllowedException() {
        super();
    }

    public NoMoreBookingAllowedException(String message) {
        super(message);
    }

    public NoMoreBookingAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoreBookingAllowedException(Throwable cause) {
        super(cause);
    }

    protected NoMoreBookingAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
