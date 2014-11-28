package org.virgonet.adonikam.donnibot;

public interface ITwitchChatServer {
    void registerListener(ITwitchChatServerListener listener);

    void start() throws PointlessBotException;
}
