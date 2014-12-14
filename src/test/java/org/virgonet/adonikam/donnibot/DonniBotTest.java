package org.virgonet.adonikam.donnibot;

import org.junit.Ignore;
import org.junit.Test;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerException;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServer;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;
import org.virgonet.adonikam.donnibot.listeners.DonniBot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DonniBotTest
{
    @Test
    public void DonniBot_GivenCommandHi_RespondsWithHello()
    {
        //Arrange
        TwitchChatServerListener donniBot = createDonniBot();

        //Act
        String response = donniBot.processCommand("hi");

        //Assert
        assertEquals("Hello!", response);
    }

    @Test
    public void DonniBot_GivenABotThatCanConnectToTwitchServer_ReceivesEvent() throws TwitchChatServerException {
        //Arrange
        TwitchChatServer server = createMockServer();
        TwitchChatServerListener donniBot = new DonniBot();

        //Act
        server.registerListener(donniBot);
        server.start();

        //Assert
        assertTrue(donniBot.isEventReceived());
    }

    // TODO Handle command parsing server-side and call processCommand directly
    @Test
    @Ignore
    @SuppressWarnings("unchecked")
    public void onEvent_whenMessageEventReceived_callsProcessCommand() {
        //Arrange
        DonniBot donniBot = new DonniBot();
        MessageEvent<PircBotX> event = (MessageEvent<PircBotX>) mock(MessageEvent.class);
        //when(event.getMessage()).thenReturn();

        //Act
        //donniBot.onEvent(event);

        //Assert
        verify(donniBot).processCommand("foo");
    }

    private TwitchChatServer createMockServer() {
        return new FakeTwitchChatServer();
    }

    private TwitchChatServerListener createDonniBot() {
        return new DonniBot();
    }
}
