package org.virgonet.adonikam.donnibot;

import com.google.common.base.Throwables;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named("twitchChatServerImpl")
public class TwitchChatServerImpl extends ListenerAdapter<PircBotX> implements org.virgonet.adonikam.donnibot.interfaces.TwitchChatServer, Listener<PircBotX> {

    protected final PircBotX pircBotX;
    protected TwitchChatServerListener listener;

    @Inject
    public TwitchChatServerImpl(BotConfigurator botConfigurator) {
        this.pircBotX = botConfigurator.createPircBotX();
    }

    @Override
    public void registerListener(TwitchChatServerListener listener) {
        // TODO Make it support more than one
        this.listener = listener;
    }

    @Override
    public void start() throws BotException {
        if (listener == null)
            throw new BotException("Pointless bot (has no listeners)");

        try {
            pircBotX.startBot();
        } catch (IOException e) {
            throw new BotException("Network error", e);
        } catch (IrcException e) {
            throw new BotException("Underlying bot crashed", e);
        }
    }

    @Override
    public void onMessage(MessageEvent event) throws Exception {
        listener.processCommand(event.getMessage());
    }
}
