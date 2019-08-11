package lab.lab34;

public class TrueException extends RuntimeException {

    public TrueException() {
    }

    TrueException(String message) {
        super(message);
    }

    public TrueException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrueException(Throwable cause) {
        super(cause);
    }

    public TrueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


