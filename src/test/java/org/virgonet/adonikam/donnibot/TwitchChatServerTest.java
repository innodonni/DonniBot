package org.virgonet.adonikam.donnibot;

import org.junit.Test;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class TwitchChatServerTest {
    @Test
    public void start_WhenCalled_UnderlyingBotIsStarted() throws IOException, IrcException, BotException {
        //Arrange
        BotConfigurator botConfigurator = mock(BotConfigurator.class);
        PircBotX bot = mock(PircBotX.class);
        when(botConfigurator.createPircBotX()).thenReturn(bot);
        TwitchChatServerImpl server = new TwitchChatServerImpl(botConfigurator);
        server.registerListener(mock(TwitchChatServerListener.class));

        //Act
        server.start();

        //Assert
        verify(bot).startBot();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void receivesChatMessage_withListenerListening_callsProcessCommand() throws Exception {
        //Arrange
        TwitchChatServerImpl server = getTwitchChatServer();
        TwitchChatServerListener mockListener = mock(TwitchChatServerListener.class);
        MessageEvent<PircBotX> mockEvent = (MessageEvent<PircBotX>) mock(MessageEvent.class);
        when(mockEvent.getMessage()).thenReturn("foo");

        //Act
        server.registerListener(mockListener);
        server.onEvent(mockEvent); // pretend that the server received a message

        //Assert
        verify(mockEvent).getMessage();
        verify(mockListener).processCommand("foo");
    }


    @Test(expected=BotException.class)
    public void start_noListenersRegistered_throwsException() throws BotException {
        //Arrange
        TwitchChatServerImpl server = getTwitchChatServer();

        //Act
        server.start();

        //Assert exception thrown
    }

    private TwitchChatServerImpl getTwitchChatServer() {
        BotConfigurator botConfigurator = mock(BotConfigurator.class);
        PircBotX bot = mock(PircBotX.class);
        when(botConfigurator.createPircBotX()).thenReturn(bot);
        return new TwitchChatServerImpl(botConfigurator);
    }
}
