package lab.lab34;

public class SizeExeption extends Exception {
    public SizeExeption() {
    }

    SizeExeption(String message) {
        super(message);
    }

    public SizeExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public SizeExeption(Throwable cause) {
        super(cause);
    }

    public SizeExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}



