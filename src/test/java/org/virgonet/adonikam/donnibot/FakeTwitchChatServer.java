package org.virgonet.adonikam.donnibot;

public class FakeTwitchChatServer implements ITwitchChatServer {
    private ITwitchChatServerListener listener;

    @Override
    public void registerListener(ITwitchChatServerListener listener) {
        this.listener = listener;
    }

    @Override
    public void start() {
        try {
            listener.onEvent(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
