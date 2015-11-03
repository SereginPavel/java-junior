package com.acme.edu;

/**
 * Class Log method overload
 * Type conversion
 * @version 1.1 2 Nov 2015
 * @author Pavel Seregin
 */
public class Logger implements LoggerState {

    //region fields
    Printer printer = new Printer();

    private LoggerStateHolder state = LoggerStateHolder.STRING_REPEAITING;
    //endregion

    public Logger() {
    }

    /**
     * Print duplicate rows.
     * If no duplicates, prints one line.
     * Considers the sum of consecutive integers,
     * If numbers are not consecutive, print the input value.
     *
     * @param message The <code>primitive: int</code> to be printed.
     */
    public void log(int message) {
        if (state == LoggerStateHolder.SIMPLE_PRINT){
            state = LoggerStateHolder.SUM_INTEGERS;
        }else if (state == LoggerStateHolder.SUM_INTEGERS) {
            state.loggerState.log(String.valueOf(message));
        } else if (state == LoggerStateHolder.STRING_REPEAITING){
            state.getLoggerState().flush();
            state = LoggerStateHolder.SUM_INTEGERS;
            state.loggerState.log(String.valueOf(message));
        }
    }

    /**
     * Print the sum of consecutive integers and prefix of the "primitive: "
     * Print string and considers the sum of duplication string and prefix of the "string: "
     * if you enter "null", the method will fail.
     * @param message If the input parameters are duplicated, The <code>string: String (x2)</code> to be printed.
     */
    @Override
    public void log(String message) {
        if (state == LoggerStateHolder.SIMPLE_PRINT){
            state = LoggerStateHolder.STRING_REPEAITING;
        }else if (state != LoggerStateHolder.STRING_REPEAITING ){
            state.getLoggerState().flush();
            state = LoggerStateHolder.STRING_REPEAITING;
            state.loggerState.log(message);
        }else{
            state.getLoggerState().log(message);
        }
    }

    /**
     * Prints an boolean and prefix of the "primitive: "
     *
     * @param message The <code>primitive: boolean</code> to be printed.
     */
    public void log(boolean message) {
        printer.print(Logger.PRIMITIVE + message);
    }

    /**
     * Prints an char and prefix of the "char: "
     *
     * @param message The <code>char: char</code> to be printed.
     */
    public void log(char message) {
        printer.print(Logger.CHAR + message);
    }

    /**
     * Prints an Object and prefix of the "reference: "
     *
     * @param message The <code>reference: Object</code> to be printed.
     */
    public void log(Object message) {
        printer.print(Logger.REFERENCE + message);
    }

    /**
     * Concatenation of symbols and array elements
     *
     * @param array print array
     */
    public void log(int[] array) {
        state = LoggerStateHolder.SIMPLE_PRINT;
        state.getLoggerState().log(array);
    }

    /**
     * Concatenation of symbols and array elements
     * @param matrix print the array in the array
     */
    public void log(int[][] matrix) {
        state = LoggerStateHolder.SIMPLE_PRINT;
        state.getLoggerState().log(matrix);
    }

    /**
     * Concatenation of symbols and array element
     * @param multiMatrix print miltiMatrix
     */
    public void log(int[][][][] multiMatrix) {
        state = LoggerStateHolder.SIMPLE_PRINT;
        state.getLoggerState().log(multiMatrix);
    }

    /**
     * Print list of arguments type String
     * @param elements The <code>String...</code> to be printed.
     */
    public void log(String... elements) {
        state = LoggerStateHolder.SIMPLE_PRINT;
        state.getLoggerState().log(elements);
    }

    /**
     * Clearing buffers
     */
    @Override
    public void flush() {
        state.getLoggerState().flush();
    }

}
