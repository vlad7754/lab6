package lab.lab34;

public class FuelException extends Exception {
    public FuelException() {
    }

    FuelException(String message) {
        super(message);
    }

    public FuelException(String message, Throwable cause) {
        super(message, cause);
    }

    public FuelException(Throwable cause) {
        super(cause);
    }

    public FuelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
