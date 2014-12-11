package org.virgonet.adonikam.donnibot;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Event;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;

import javax.inject.Named;

@Named("donniBot")
public class DonniBot implements TwitchChatServerListener {
    @Override
    public boolean isEventReceived() {
        return eventReceived;
    }

    private boolean eventReceived = false;

    @Override
    public String processCommand(String command) {
        return "Hello!";
    }

    @Override
    public void onEvent(Event<PircBotX> event) throws Exception {
        eventReceived = true;
    }
}
