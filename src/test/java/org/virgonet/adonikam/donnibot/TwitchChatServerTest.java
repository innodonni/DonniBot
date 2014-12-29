package org.virgonet.adonikam.donnibot;

import org.junit.Test;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.virgonet.adonikam.donnibot.config.BotConfigurator;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerException;
import org.virgonet.adonikam.donnibot.impl.TwitchChatServerImpl;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class TwitchChatServerTest {
    @Test
    public void start_WhenCalled_UnderlyingBotIsStarted() throws IOException, IrcException, TwitchChatServerException {
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
    public void start_WhenCalled_ServerAddedAsListenerOfUnderlyingBot() throws IOException, IrcException, TwitchChatServerException {
        //Arrange
        BotConfigurator botConfigurator = mock(BotConfigurator.class);
        PircBotX bot = mock(PircBotX.class);
        when(botConfigurator.createPircBotX()).thenReturn(bot);

        //Act
        TwitchChatServerImpl server = new TwitchChatServerImpl(botConfigurator);

        //Assert
        verify(botConfigurator).addListener(server);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void receivesChatMessage_withListenerListening_callsProcessCommand() throws Exception {
        //Arrange
        TwitchChatServerImpl server = getTwitchChatServer();
        TwitchChatServerListener mockListener = mock(TwitchChatServerListener.class);
        GenericMessageEvent mockEvent = mock(GenericMessageEvent.class);
        when(mockEvent.getMessage()).thenReturn("?foo arg1 arg2");

        //Act
        server.registerListener(mockListener);
        server.onGenericMessage(mockEvent); // pretend that the server received a message

        //Assert
        verify(mockEvent, atLeastOnce()).getMessage();
        verify(mockListener).processCommand("foo", "arg1", "arg2");
    }

    @Test
    public void receivesChatMessage_afterCommandProcessed_RespondsWithReturnedString() throws Exception {
        //Arrange
        TwitchChatServerImpl server = getTwitchChatServer();
        TwitchChatServerListener mockListener = mock(TwitchChatServerListener.class);
        GenericMessageEvent mockEvent = mock(GenericMessageEvent.class);
        when(mockEvent.getMessage()).thenReturn("?hi");
        when(mockListener.processCommand("hi")).thenReturn("Hello!");

        //Act
        server.registerListener(mockListener);
        server.onGenericMessage(mockEvent);

        //Assert
        verify(mockEvent, atLeastOnce()).respond("Hello!");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void afterCommandProcessed_ResponseIsNull_DoesNotRespond() throws Exception {
        //Arrange
        TwitchChatServerImpl server = getTwitchChatServer();
        TwitchChatServerListener mockListener = mock(TwitchChatServerListener.class);
        GenericMessageEvent mockEvent = mock(GenericMessageEvent.class);
        when(mockEvent.getMessage()).thenReturn("?foo arguments");
        when(mockListener.processCommand("foo", "arguments")).thenReturn(null);

        //Act
        server.registerListener(mockListener);
        server.onGenericMessage(mockEvent);

        //Assert
        verify(mockEvent, times(0)).respond(null);
    }

    @Test(expected=TwitchChatServerException.class)
    public void start_noListenersRegistered_throwsException() throws TwitchChatServerException {
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
