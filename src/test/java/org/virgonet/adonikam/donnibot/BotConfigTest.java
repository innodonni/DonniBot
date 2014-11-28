package org.virgonet.adonikam.donnibot;

import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertNotNull;

public class BotConfigTest {
    @Test
    @Ignore
    public void getPircBotConfiguration_givenValidData_returnsConfiguration() {
        //Arrange
        Object configuration;
        // TODO Create an in-memory InputStream
        InputStream fileStream = null;
        // TODO Test that fileStream doesn't accept null
        BotConfig botConfig = new BotConfig(fileStream);

        //Act
        configuration = botConfig.createPircBotConfiguration();

        //Assert
        assertNotNull(configuration);
    }
}