package org.virgonet.adonikam.donnibot.impl;

import org.pircbotx.hooks.types.GenericMessageEvent;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.virgonet.adonikam.donnibot.config.BotConfigurator;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerException;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Arrays;

@Named
public class TwitchChatServerImpl extends ListenerAdapter<PircBotX> implements org.virgonet.adonikam.donnibot.interfaces.TwitchChatServer, Listener<PircBotX> {

    protected final PircBotX pircBotX;
    protected TwitchChatServerListener listener;

    @Inject
    public TwitchChatServerImpl(BotConfigurator botConfigurator) {
        botConfigurator.addListener(this);
        this.pircBotX = botConfigurator.createPircBotX();
    }

    @Override
    public void registerListener(TwitchChatServerListener listener) {
        // TODO Make it support more than one
        this.listener = listener;
    }

    @Override
    public void start() throws TwitchChatServerException {
        if (listener == null)
            throw new TwitchChatServerException("Pointless bot (has no listeners)");

        try {
            pircBotX.startBot();
        } catch (IOException e) {
            throw new TwitchChatServerException("Network error", e);
        } catch (IrcException e) {
            throw new TwitchChatServerException("Underlying bot crashed", e);
        }
    }

    @Override
    public void onGenericMessage(GenericMessageEvent event) throws Exception {
        String message = event.getMessage();
        if (message.startsWith("?")) {
            String[] strings = message.split(" ");
            String[] args = new String[] {};
            if (strings.length > 1) {
                args = Arrays.copyOfRange(strings, 1, strings.length);
            }
            String response = listener.processCommand(strings[0].substring(1), args);
            if (response != null)
                event.respond(response);
        }
    }
}
