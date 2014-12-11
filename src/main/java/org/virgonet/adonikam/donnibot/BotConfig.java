package org.virgonet.adonikam.donnibot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.inject.Named;

@Configuration
@Profile("production")
@PropertySource(value = "file:config/defaults.properties", ignoreResourceNotFound = false)
@PropertySource(value = "file:config/donnibot.properties", ignoreResourceNotFound = true)
@Named("botConfig")
public class BotConfig {
    @Value("${org.virgonet.adonikam.donnibot.botName}")
    protected String botName;
    @Value("${org.virgonet.adonikam.donnibot.channelName}")
    protected String channelName;
    @Value("${org.virgonet.adonikam.donnibot.serverHostName}")
    protected String serverHostName;
    @Value("${org.virgonet.adonikam.donnibot.serverPort}")
    protected int serverPort;
    @Value("${org.virgonet.adonikam.donnibot.serverPassword}")
    protected String serverPassword;

    protected BotConfig() {

    }

    public BotConfig(String botName, String channelName, String serverHostName, int serverPort, String serverPassword) {
        this.botName = botName;
        this.channelName = channelName;
        this.serverHostName = serverHostName;
        this.serverPort = serverPort;
        this.serverPassword = serverPassword;
    }

    @Bean // cannot be @Named or the bean won't be initialised
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public String getBotName() {
        return botName;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getServerHostName() {
        return serverHostName;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getServerPassword() {
        return serverPassword;
    }
}
