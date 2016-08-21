package by.pvt.exception;

/**
 * This class catch exceptions from service layer
 */
public class ServiceException extends Exception {

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
