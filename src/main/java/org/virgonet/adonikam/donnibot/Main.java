package org.virgonet.adonikam.donnibot;

import com.google.common.base.Preconditions;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.virgonet.adonikam.donnibot.config.TwitchChatServerModule;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerException;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServer;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;
import org.virgonet.adonikam.donnibot.listeners.DonniBot;
import org.virgonet.adonikam.donnibot.listeners.JSCommand;

@SuppressWarnings("WeakerAccess")
public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Injector injector = Guice.createInjector(new TwitchChatServerModule());

        try {
            TwitchChatServer chatServer = injector.getInstance(TwitchChatServer.class);
            //TwitchChatServerListener listener = injector.getInstance(JSCommand.class);
            TwitchChatServerListener listener2 = injector.getInstance(DonniBot.class);
            chatServer.registerListener(listener2);
            chatServer.start();
        } catch (TwitchChatServerException e) {
            handleFatalError(e);
        }
    }

    void handleFatalError(Throwable throwable) {
        Preconditions.checkNotNull(throwable);
        throwable.printStackTrace(System.err);
    }

}
