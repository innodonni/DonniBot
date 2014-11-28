import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.virgonet.adonikam.donnibot.BotConfig;
import org.virgonet.adonikam.donnibot.TwitchChatServer;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        // TODO Put in a real file!
        new TwitchChatServer(new PircBotX(new BotConfig(null).createPircBotConfiguration()));
    }
}
