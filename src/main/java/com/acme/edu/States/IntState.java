package com.acme.edu.states;

import com.acme.edu.printer.PrinterManager;
import com.acme.edu.printer.PrinterException;

/**
 * Created by Павел on 02.11.2015.
 */
public class IntState extends LoggerState {

    //region fields
    private int buffer = 0;
    //endregion

    //region constructor
    ///for testability

    /**
     * Initialization of types of printing
     *
     * @param printers vararg types printers
     */
    public IntState(PrinterManager... printers) {
        super(printers);
    }
    //endregion


    //region methods
    /**
     * Finds the sum of integers
     * @param message
     */
    @Override
    public void log(String message) throws PrinterException {
        checkMaxAndOverFlow(Integer.parseInt(message));
        buffer += Integer.parseInt(message);
    }

    /**
     * Clearing buffers
     */
    @Override
    public void flush() throws PrinterException {
        printState(PRIMITIVE + String.valueOf(buffer));
        buffer = 0;
    }

    private void checkMaxAndOverFlow(int message) throws PrinterException {
        if (message == Integer.MAX_VALUE
                || message == Integer.MIN_VALUE
                || (long) message + buffer > Integer.MAX_VALUE
                || (long) message + buffer < Integer.MIN_VALUE) {
            flush();
        }
    }
    //endregion
}
