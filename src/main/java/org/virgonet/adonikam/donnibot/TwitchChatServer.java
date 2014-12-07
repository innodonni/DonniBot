package org.virgonet.adonikam.donnibot;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.IOException;

public class TwitchChatServer extends ListenerAdapter<PircBotX> implements ITwitchChatServer, Listener<PircBotX> {

    private final PircBotX pircBotX;
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
    public void start() throws PointlessBotException {
        if (listener == null)
            throw new PointlessBotException();

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
    public void onMessage(MessageEvent event) throws Exception {
        listener.processCommand(event.getMessage());
    }
}
