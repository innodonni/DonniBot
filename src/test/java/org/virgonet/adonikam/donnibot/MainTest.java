package org.virgonet.adonikam.donnibot;

import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class MainTest {
    @Test(expected=NullPointerException.class)
    public void handleFatalError_whenPassedNull_throwsNPE() {
        //Arrange
        Main main = new Main();

        //Act
        main.handleFatalError(null);

        //Assert an exception was thrown
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    @Test
    public void handleFatalError_whenPassedThrowable_printsStackTrace() {
        //Arrange
        Main main = new Main();
        Throwable throwable = mock(Throwable.class);

        //Act
        main.handleFatalError(throwable);

        //Assert
        verify(throwable).printStackTrace(any(PrintStream.class));

    }
}
