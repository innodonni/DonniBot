package org.virgonet.adonikam.donnibot.interfaces;

import org.virgonet.adonikam.donnibot.PointlessBotException;

@SuppressWarnings("WeakerAccess")
public interface TwitchChatServer {
    void registerListener(TwitchChatServerListener listener);

    void start() throws PointlessBotException;
}
