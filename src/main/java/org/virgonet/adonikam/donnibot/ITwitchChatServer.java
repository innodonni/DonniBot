package org.virgonet.adonikam.donnibot;

import com.sun.corba.se.pept.transport.ListenerThread;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Listener;

public interface ITwitchChatServer {
    void registerListener(ITwitchChatServerListener listener);

    void start();
}
