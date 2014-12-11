package org.virgonet.adonikam.donnibot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BotConfigurator {

    protected BotConfig botConfig;

    @Inject
    public BotConfigurator(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    public PircBotX createPircBotX() {
        return new PircBotX(createPircBotConfiguration(botConfig));
    }

    protected Configuration<PircBotX> createPircBotConfiguration(BotConfig botConfig) {
        return new Configuration.Builder<>()
                .setName(botConfig.getBotName())
                .setServerPassword(botConfig.getServerPassword())
                .setServerPort(botConfig.getServerPort())
                .setServerHostname(botConfig.getServerHostName())
                .addAutoJoinChannel(botConfig.getChannelName())
                .buildConfiguration();
    }
}
