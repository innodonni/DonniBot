package org.virgonet.adonikam.donnibot.interfaces;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.Listener;

@SuppressWarnings("WeakerAccess")
public interface TwitchChatServerListener extends Listener<PircBotX> {
    boolean isEventReceived();

    String processCommand(String command);

    @Override
    void onEvent(Event<PircBotX> event) throws Exception;
}
