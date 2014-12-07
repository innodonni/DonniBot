package org.virgonet.adonikam.donnibot;

@SuppressWarnings("WeakerAccess")
public interface ITwitchChatServer {
    void registerListener(ITwitchChatServerListener listener);

    void start() throws PointlessBotException;
}
