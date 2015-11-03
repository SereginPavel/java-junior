package com.acme.edu;

/**
 * Created by Павел on 02.11.2015.
 */
public class StringState implements LoggerState {
    Printer printer;
    private static String previousLine = "";
    private int buffer = 1;

    public StringState(Printer mock) {
        this.printer = mock;
    }

    @Override
    public void log(String message) {
        if (message.isEmpty()){
            return;
        }
        if (previousLine.equals(message)){
            buffer++;
        }else if (previousLine != ""){
            flush();
        }
        previousLine = message;
    }

    @Override
    public void flush() {
        if (buffer == 1 && !previousLine.isEmpty()){
            printer.print(STRING + previousLine);
        }else if(buffer > 1 ){
            printer.print(String.format("%s%s (x%d)", STRING, previousLine, buffer));
        }
        buffer = 1;
        previousLine = "";
    }

    @Override
    public void log(int[] array) {

    }

    @Override
    public void log(int[][] matrix) {
    }

    @Override
    public void log(int[][][][] multiMatrix) {
    }

    @Override
    public void log(String... elements) {
    }
}
