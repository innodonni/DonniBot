package org.virgonet.adonikam.donnibot;

import org.junit.Test;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServer;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void DonniBot_GivenABotThatCanConnectToTwitchServer_ReceivesEvent() throws PointlessBotException {
        //Arrange
        TwitchChatServer server = createMockServer();
        TwitchChatServerListener donniBot = new DonniBot();

        //Act
        server.registerListener(donniBot);
        server.start();

        //Assert
        assertTrue(donniBot.isEventReceived());
    }

    private TwitchChatServer createMockServer() {
        return new FakeTwitchChatServer();
    }

    private TwitchChatServerListener createDonniBot() {
        return new DonniBot();
    }
}
