package org.virgonet.adonikam.donnibot;

import javax.inject.Named;

@SuppressWarnings("UnusedDeclaration")
@Named
public class BotConfig {
    private String botName;
    private String channelName;
    private String serverHostName;
    private int serverPort;
    private String serverPassword;

    public BotConfig(String botName, String channelName, String serverHostName, int serverPort, String serverPassword) {
        this.botName = botName;
        this.channelName = channelName;
        this.serverHostName = serverHostName;
        this.serverPort = serverPort;
        this.serverPassword = serverPassword;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getServerHostName() {
        return serverHostName;
    }

    public void setServerHostName(String serverHostName) {
        this.serverHostName = serverHostName;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerPassword() {
        return serverPassword;
    }

    public void setServerPassword(String serverPassword) {
        this.serverPassword = serverPassword;
    }
}
