package com.acme.edu.logger;


/**
 * Created by Павел on 05.11.2015.
 */
public class LogException extends Exception {

    /**
     * Incorrect data
     * @param cause The <code>Throwable </code> be printed to the console.
     */
    public LogException(Throwable cause) {
        super(cause);
    }
}