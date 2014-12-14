package org.virgonet.adonikam.donnibot.interfaces;

@SuppressWarnings("WeakerAccess")
public interface TwitchChatServer {
    void registerListener(TwitchChatServerListener listener);

    void start() throws TwitchChatServerException;
}
