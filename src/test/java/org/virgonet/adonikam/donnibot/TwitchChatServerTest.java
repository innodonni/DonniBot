package org.virgonet.adonikam.donnibot;

import org.junit.Test;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class TwitchChatServerTest {
    @Test
    public void start_WhenCalled_UnderlyingBotIsStarted() throws IOException, IrcException, PointlessBotException {
        //Arrange
        PircBotX mockedPircBot = mock(PircBotX.class);
        TwitchChatServer server = new TwitchChatServer(mockedPircBot);
        server.registerListener(mock(ITwitchChatServerListener.class));
        //Act
        server.start();

        //Assert
        verify(mockedPircBot).startBot();
    }

    @Test
    public void receivesChatMessage_withListenerListening_callsProcessCommand() throws Exception {
        //Arrange
        TwitchChatServer server = getTwitchChatServer();
        ITwitchChatServerListener mockListener = mock(ITwitchChatServerListener.class);
        MessageEvent<PircBotX> mockEvent = (MessageEvent<PircBotX>) mock(MessageEvent.class);
        when(mockEvent.getMessage()).thenReturn("foo");

        //Act
        server.registerListener(mockListener);
        server.onEvent(mockEvent); // pretend that the server received a message

        //Assert
        verify(mockEvent).getMessage();
        verify(mockListener).processCommand("foo");
    }


    @Test(expected=PointlessBotException.class)
    public void start_noListenersRegistered_throwsException() throws PointlessBotException {
        //Arrange
        TwitchChatServer server = getTwitchChatServer();

        //Act
        server.start();

        //Assert exception thrown
    }

    private TwitchChatServer getTwitchChatServer() {
        PircBotX mockedPircBot = mock(PircBotX.class);
        return new TwitchChatServer(mockedPircBot);
    }
}
