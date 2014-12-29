package org.virgonet.adonikam.donnibot;

import org.junit.Test;
import org.virgonet.adonikam.donnibot.interfaces.SafeJSEngineException;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;
import org.virgonet.adonikam.donnibot.listeners.JSCommand;
import org.virgonet.adonikam.scripting.SafeJSEngine;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JSCommandTest {

    @Test
    public void JSCommand_GivenMathExpression_EvaluatedAndResultReturned() throws Exception {
        //Arrange
        TwitchChatServerListener jsCommand = createJSCommand();

        //Act
        String response = jsCommand.processCommand("js", "1+1");

        //Assert
        assertEquals("2", response);
    }

    private TwitchChatServerListener createJSCommand() throws Exception {
        SafeJSEngine safeJSEngine = mock(SafeJSEngine.class);
        when(safeJSEngine.eval("1+1")).thenReturn("2");
        return new JSCommand(safeJSEngine);
    }
}
