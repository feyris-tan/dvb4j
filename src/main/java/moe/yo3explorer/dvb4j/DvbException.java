package moe.yo3explorer.dvb4j;

public class DvbException extends RuntimeException
{
    public DvbException() {
    }

    public DvbException(String message) {
        super(message);
    }

    public DvbException(String message, Throwable cause) {
        super(message, cause);
    }

    public DvbException(Throwable cause) {
        super(cause);
    }

    public DvbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
