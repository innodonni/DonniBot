package org.virgonet.adonikam.donnibot;

import org.pircbotx.PircBotX;

@SuppressWarnings("WeakerAccess")
public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        // TODO Put in a real file!
        try {
            new TwitchChatServer(new PircBotX(new BotConfig(null).createPircBotConfiguration())).start();
        } catch (PointlessBotException e) {
            handleFatalError(e);
        }
    }

    void handleFatalError(Throwable throwable) {
        // TODO test
        // throwable.printStackTrace();
    }
}
