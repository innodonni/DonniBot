package org.virgonet.adonikam.donnibot;

import org.junit.Test;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TwitchTest {
    @Test
    public void start_WhenCalled_UnderlyingBotIsStarted() throws IOException, IrcException {
        //Arrange
        PircBotX mockedPircBot = mock(PircBotX.class);

        TwitchChatServer server = new TwitchChatServer(mockedPircBot);

        //Act
        server.start();

        //Assert
        verify(mockedPircBot).startBot();
    }

    @Test
    public void receivesChatMessage_listenerListening_callsProcessCommand() throws Exception {
        //Arrange
        PircBotX mockedPircBot = mock(PircBotX.class);
        TwitchChatServer server = new TwitchChatServer(mockedPircBot);

        //Act
        ITwitchChatServerListener mockListener = mock(ITwitchChatServerListener.class);
        server.registerListener(mockListener);
        MessageEvent<PircBotX> mockEvent = (MessageEvent<PircBotX>) mock(MessageEvent.class);
        server.onEvent(mockEvent);

        //Assert
        verify(mockListener).processCommand("hi");
    }
}
