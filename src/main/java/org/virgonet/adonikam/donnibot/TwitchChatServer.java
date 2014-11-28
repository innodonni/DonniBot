package org.virgonet.adonikam.donnibot;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.Listener;

import java.io.IOException;

public class TwitchChatServer implements ITwitchChatServer, Listener<PircBotX> {

    private PircBotX pircBotX;
    private ITwitchChatServerListener listener;

    public TwitchChatServer(PircBotX pircBotX) {
        this.pircBotX = pircBotX;
    }

    @Override
    public void registerListener(ITwitchChatServerListener listener) {
        // TODO Make it support more than one

        this.listener = listener;
    }

    @Override
    public void start() {
        // TODO Create my own exception type
        try {
            pircBotX.startBot();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEvent(Event<PircBotX> event) throws Exception {
        listener.processCommand("hi");
    }
}
