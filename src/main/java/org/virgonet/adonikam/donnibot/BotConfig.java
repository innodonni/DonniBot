package org.virgonet.adonikam.donnibot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import java.io.InputStream;

@SuppressWarnings("WeakerAccess")
public class BotConfig {
    public static final java.lang.String CHANNEL_NAME = "y";
    public static final String SERVER_HOSTNAME = "z";
    public static final int SERVER_PORT = 1;
    public static final String SERVER_PASSWORD = "";
    public static final String BOT_NAME = "x";

    /**
     *
     * @param configurationData the contents of a Java properties file
     */
    public BotConfig(InputStream configurationData) {
        // TODO Get properties out of stream
    }

    public Configuration<PircBotX> createPircBotConfiguration() {
        return null;
//        return new Configuration.Builder<>()
//                .setName(BotConfig.BOT_NAME)
//                .setServerPassword(BotConfig.SERVER_PASSWORD)
//                .setServerPort(BotConfig.SERVER_PORT)
//                .setServerHostname(BotConfig.SERVER_HOSTNAME)
//                .addAutoJoinChannel(BotConfig.CHANNEL_NAME)
//                .buildConfiguration();
    }
}
