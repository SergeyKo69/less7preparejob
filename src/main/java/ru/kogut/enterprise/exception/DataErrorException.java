package ru.kogut.enterprise.exception;

/**
 * @author S.Kogut on 2019-04-12
 */
public class DataErrorException extends RuntimeException {

    public DataErrorException(String message) {
        super(message);
    }
}
