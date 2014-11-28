package org.virgonet.adonikam.donnibot;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DonniBotTest
{
    @Test
    public void DonniBot_GivenCommandHi_RespondsWithHello()
    {
        //Arrange
        ITwitchChatServerListener donniBot = createDonniBot();

        //Act
        String response = donniBot.processCommand("hi");

        //Assert
        assertEquals("Hello!", response);
    }

    @Test
    public void DonniBot_GivenABotThatCanConnectToTwitchServer_ReceivesEvent() throws PointlessBotException {
        //Arrange
        ITwitchChatServer server = createMockServer();
        ITwitchChatServerListener donniBot = new DonniBot();

        //Act
        server.registerListener(donniBot);
        server.start();

        //Assert
        assertTrue(donniBot.isEventReceived());
    }

    private ITwitchChatServer createMockServer() {
        return new FakeTwitchChatServer();
    }

    private ITwitchChatServerListener createDonniBot() {
        return new DonniBot();
    }
}
