package com.acme.edu;

/**
 * Created by Павел on 02.11.2015.
 */
interface LoggerState {
    void log(String message);
    void flush();
}
