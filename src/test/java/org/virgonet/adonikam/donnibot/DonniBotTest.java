package org.virgonet.adonikam.donnibot;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.pircbotx.*;
import org.pircbotx.dcc.DccHandler;
import org.pircbotx.exception.IrcException;
import org.pircbotx.output.OutputCAP;
import org.pircbotx.output.OutputDCC;
import org.pircbotx.output.OutputIRC;
import org.pircbotx.output.OutputRaw;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DonniBotTest
{
    @Test
    public void DonniBot_GivenCommandHi_RespondsWithHello()
    {
        //Arrange
        ITwitchChatServerListener donniBot = createDonniBot();

        //Act
        String response = donniBot.processCommand("hi");

        //Assert
        assertEquals("Hello!", response);
    }

    @Test
    public void DonniBot_GivenABotThatCanConnectToTwitchServer_ReceivesEvent()
    {
        //Arrange
        ITwitchChatServer server = createFakeBotFacade();
        ITwitchChatServerListener donniBot = new DonniBot();

        //Act
        server.registerListener(donniBot);
        server.start();

        //Assert
        assertTrue(donniBot.isEventReceived());
    }

    private ITwitchChatServer createFakeBotFacade() {
        return new FakeTwitchChatServer();
    }

    private static Configuration<PircBotX> getConfiguration() {
        return new Configuration.Builder<>()
                .setName(BotConfig.BOT_NAME)
                .setServerPassword(BotConfig.SERVER_PASSWORD)
                .setServerPort(BotConfig.SERVER_PORT)
                .setServerHostname(BotConfig.SERVER_HOSTNAME)
                .addAutoJoinChannel(BotConfig.CHANNEL_NAME)
                .buildConfiguration();
    }

    private ITwitchChatServer createTwitchServer()
    {
        return new TwitchChatServer(null);
    }

    private PircBotX createFakeBot() {
        return new PircBotX(getConfiguration()) {
            @Override
            public void startBot() throws IOException, IrcException {
                //
            }

            @Override
            public void stopBotReconnect() {
                //
            }

            @Override
            protected void connect() throws IOException, IrcException {
                //
            }

            @Override
            protected void changeSocket(Socket socket) throws IOException {
                //
            }

            @Override
            protected void startLineProcessing() {
                //
            }

            @Override
            protected void sendRawLineToServer(String line) {
                //
            }

            @Override
            protected void loggedIn(String nick) {
                //
            }

            @Override
            public OutputRaw sendRaw() {
                return null;
            }

            @Override
            public OutputIRC sendIRC() {
                return null;
            }

            @Override
            public OutputCAP sendCAP() {
                return null;
            }

            @Override
            public OutputDCC sendDCC() {
                return null;
            }

            @Override
            protected void setNick(String nick) {
                //
            }

            @Override
            public String getNick() {
                return null;
            }

            @Override
            public boolean isConnected() {
                return false;
            }

            @Override
            public String toString() {
                return null;
            }

            @Override
            public User getUserBot() {
                return null;
            }

            @Override
            public ServerInfo getServerInfo() {
                return null;
            }

            @Override
            public InetAddress getLocalAddress() {
                return null;
            }

            @Override
            protected ImmutableMap<String, String> reconnectChannels() {
                return null;
            }

            @Override
            protected void shutdown() {
                //
            }

            @Override
            protected void shutdown(boolean noReconnect) {
                //
            }

            @Override
            public int compareTo(PircBotX other) {
                return 0;
            }

            @Override
            public State getState() {
                return null;
            }

            @Override
            public int getBotId() {
                return 0;
            }

            @Override
            public Configuration<PircBotX> getConfiguration() {
                return null;
            }

            @Override
            public InputParser getInputParser() {
                return null;
            }

            @Override
            public UserChannelDao<User, Channel> getUserChannelDao() {
                return null;
            }

            @Override
            public DccHandler getDccHandler() {
                return null;
            }

            @Override
            protected Socket getSocket() {
                return null;
            }

            @Override
            public List<String> getEnabledCapabilities() {
                return null;
            }
        };
    }

    private ITwitchChatServerListener createDonniBot() {
        return new DonniBot();
    }
}
