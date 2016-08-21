package by.pvt.exception;

/**
 * This class return exception for DAO classes
 */
public class DAOException extends Exception {


    public DAOException(String message, Exception cause){
        super(message, cause);
    }
}
