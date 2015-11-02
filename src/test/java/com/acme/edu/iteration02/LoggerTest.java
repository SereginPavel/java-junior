package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    private static final String SEP = System.lineSeparator();
    private Logger logger;

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        logger = new Logger();
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion


    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        Logger.log("str 1");
        logger.log(1);
        logger.log(2);
        Logger.log("str 2");
        logger.log(0);
        logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + SEP +
                        "primitive: 3" + SEP +
                        "string: str 2" + SEP +
                        "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        Logger.log("str 1");
        logger.log(10);
        logger.log(Integer.MAX_VALUE);
        Logger.log("str 2");
        logger.log(0);
        logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + SEP +
                        "primitive: 10" + SEP +
                        "primitive: " + Integer.MAX_VALUE + SEP +
                        "string: str 2" + SEP +
                        "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        Logger.log("str 1");
        logger.log((byte)10);
        logger.log((byte)Byte.MAX_VALUE);
        Logger.log("str 2");
        logger.log(0);
        logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1"+ SEP +
            "primitive: 10"+ SEP +
            "primitive: " + Byte.MAX_VALUE + SEP +
            "string: str 2"+ SEP +
            "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        Logger.log("str 1");
        Logger.log("str 2");
        Logger.log("str 2");
        logger.log(0);
        Logger.log("str 2");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1" + SEP +
            "string: str 2 (x2)" + SEP +
            "primitive: 0" + SEP +
            "string: str 2" + SEP +
            "string: str 3 (x3)"+ SEP
        );
        //endregion
    }

}