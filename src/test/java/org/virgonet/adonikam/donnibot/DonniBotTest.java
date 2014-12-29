package org.virgonet.adonikam.donnibot;

import org.junit.Ignore;
import org.junit.Test;
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

    private TwitchChatServerListener createDonniBot() {
        return new DonniBot();
    }
}
