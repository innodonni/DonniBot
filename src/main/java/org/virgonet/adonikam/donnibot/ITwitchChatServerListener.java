package org.virgonet.adonikam.donnibot;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.Listener;

@SuppressWarnings("WeakerAccess")
public interface ITwitchChatServerListener extends Listener<PircBotX> {
    boolean isEventReceived();

    String processCommand(String command);

    @Override
    void onEvent(Event<PircBotX> event) throws Exception;
}
