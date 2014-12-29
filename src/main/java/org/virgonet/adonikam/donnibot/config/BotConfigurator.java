package org.virgonet.adonikam.donnibot.config;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.virgonet.adonikam.donnibot.impl.TwitchChatServerImpl;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BotConfigurator {

    protected final BotConfig botConfig;
    private Configuration.Builder<PircBotX> botXBuilder;

    @Inject
    public BotConfigurator(BotConfig botConfig) {
        this.botConfig = botConfig;
        botXBuilder = new Configuration.Builder<>()
                .setName(botConfig.getBotName())
                .setServerPassword(botConfig.getServerPassword())
                .setServerPort(botConfig.getServerPort())
                .setServerHostname(botConfig.getServerHostName())
                .addAutoJoinChannel(botConfig.getChannelName());
    }

    public PircBotX createPircBotX() {
        return new PircBotX(createPircBotConfiguration(botConfig));
    }

    protected Configuration<PircBotX> createPircBotConfiguration(BotConfig botConfig) {
        return botXBuilder
                .buildConfiguration();
    }

    public void addListener(ListenerAdapter<PircBotX> listener) {
        botXBuilder.addListener(listener);
    }
}
