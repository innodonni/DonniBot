package org.virgonet.adonikam.donnibot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServer;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;

@SuppressWarnings("WeakerAccess")
public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        new Main().run(args);
    }

    private void run(String[] args) {
        Injector injector = Guice.createInjector(new TwitchChatServerModule());

        try {
            TwitchChatServer chatServer = injector.getInstance(TwitchChatServer.class);
            TwitchChatServerListener listener = injector.getInstance(DonniBot.class);
            chatServer.registerListener(listener);
            chatServer.start();
        } catch (PointlessBotException e) {
            handleFatalError(e);
        }
    }

    void handleFatalError(Throwable throwable) {
        // TODO test
//        throwable.printStackTrace();
    }

}
