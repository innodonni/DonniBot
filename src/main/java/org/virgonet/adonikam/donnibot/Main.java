package org.virgonet.adonikam.donnibot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServer;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;

import javax.inject.Named;

@SuppressWarnings("WeakerAccess")
public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        new Main().run(args);
    }

    @Named
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    private void run(String[] args) {
        CommandLinePropertySource argSource = new SimpleCommandLinePropertySource(args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().addActiveProfile("production");
        context.getEnvironment().getPropertySources().addFirst(argSource);
        context.register(BotConfig.class);
        context.register(BotConfigurator.class);
        context.register(DonniBot.class);
        context.register(TwitchChatServerImpl.class);
        context.registerShutdownHook(); // no need to call close() in finally block
        context.refresh();
        context.start();

        try {
            TwitchChatServer chatServer = context.getBean(TwitchChatServer.class);
            TwitchChatServerListener listener = context.getBean(DonniBot.class);
            chatServer.registerListener(listener);
            chatServer.start();
        } catch (PointlessBotException e) {
            handleFatalError(e);
        }
    }

    void handleFatalError(Throwable throwable) {
        // TODO test
        // throwable.printStackTrace();
    }
}
