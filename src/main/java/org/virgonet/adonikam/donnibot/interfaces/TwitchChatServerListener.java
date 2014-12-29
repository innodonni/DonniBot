package org.virgonet.adonikam.donnibot.interfaces;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.Listener;

@SuppressWarnings("WeakerAccess")
public interface TwitchChatServerListener {

    String processCommand(String command, String... args);

}
