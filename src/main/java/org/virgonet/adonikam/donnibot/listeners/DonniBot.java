package org.virgonet.adonikam.donnibot.listeners;

import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;

import javax.inject.Named;

@Named
public class DonniBot implements TwitchChatServerListener {

    private boolean eventReceived = false;

    @Override
    public String processCommand(String commandName, String... args) {
        return "Hello!";
    }

}
