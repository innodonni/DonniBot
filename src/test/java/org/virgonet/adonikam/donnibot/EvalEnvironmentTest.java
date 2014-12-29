package org.virgonet.adonikam.donnibot;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.virgonet.adonikam.scripting.EvalEnvironment;

public class EvalEnvironmentTest {
    @Test
    public void execute_givenTwoPlusTwo_Returns4() throws Exception {
        //Arrange
        EvalEnvironment env = new EvalEnvironment("2+2");

        //Act
        String result = env.execute();

        //Assert
        Assert.assertEquals("4", result);
    }
    @Test
    public void execute_givenSafeStatement_ReturnsUndefined() throws Exception {
        //Arrange
        EvalEnvironment env = new EvalEnvironment("void(0)");

        //Act
        String result = env.execute();

        //Assert
        Assert.assertEquals("undefined", result);
    }
    @Test
    public void execute_givenJunk_ReturnsErrorMessage() throws Exception {
        //Arrange
        EvalEnvironment env = new EvalEnvironment("junk");

        //Act
        String result = env.execute();

        //Assert exception handled
        Assert.assertEquals("JS error", result.substring(0, 8));
    }
    @Ignore
    @Test
    public void execute_givenPrintStatement_ReturnsSecurityWarning() throws Exception {
        //Arrange
        EvalEnvironment env = new EvalEnvironment("print('hi')");

        //Act
        String result = env.execute();

        //Assert
        Assert.assertEquals("JS error", result.substring(0, 8));
    }
}
