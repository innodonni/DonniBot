package org.virgonet.adonikam.donnibot;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.virgonet.adonikam.scripting.SafeJSEngine;
import org.virgonet.adonikam.scripting.SafeJSEngineImpl;

public class SafeJSEngineTest {
    @Test
    @Ignore
    public void eval_passedTwoPlusOne_ReturnsThree() throws Exception {
        //Arrange
        SafeJSEngine safeJSEngine = new SafeJSEngineImpl();

        //Act
        String result = safeJSEngine.eval("2+1");

        //Assert
        Assert.assertEquals("3", result);
    }
}
