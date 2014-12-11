package org.virgonet.adonikam.donnibot;

import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServer;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;

public class FakeTwitchChatServer implements TwitchChatServer {
    private TwitchChatServerListener listener;

    @Override
    public void registerListener(TwitchChatServerListener listener) {
        this.listener = listener;
    }

    @Override
    public void start() {
        try {
            listener.onEvent(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
