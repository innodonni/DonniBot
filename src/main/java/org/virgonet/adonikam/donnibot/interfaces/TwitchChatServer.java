package org.virgonet.adonikam.donnibot.interfaces;

import org.virgonet.adonikam.donnibot.BotException;

@SuppressWarnings("WeakerAccess")
public interface TwitchChatServer {
    void registerListener(TwitchChatServerListener listener);

    void start() throws BotException;
}
