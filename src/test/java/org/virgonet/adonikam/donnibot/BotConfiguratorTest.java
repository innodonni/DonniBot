package org.virgonet.adonikam.donnibot;

import org.junit.Test;
import org.pircbotx.PircBotX;
import org.virgonet.adonikam.donnibot.config.BotConfig;
import org.virgonet.adonikam.donnibot.config.BotConfigurator;

import static org.junit.Assert.assertNotNull;

public class BotConfiguratorTest {
    @Test
    public void createPircBotX_givenValidData_returnsBot() {
        //Arrange
        BotConfigurator botConfigurator = new BotConfigurator(validBotConfig());

        //Act
        PircBotX bot = botConfigurator.createPircBotX();

        //Assert
        assertNotNull(bot);
    }

    static BotConfig validBotConfig() {
        return new BotConfig("ValidBot", "#validchannel", "valid.example.com", 6667, "validPassword");
    }
}