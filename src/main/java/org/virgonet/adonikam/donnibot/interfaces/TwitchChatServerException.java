package org.virgonet.adonikam.donnibot.interfaces;

@SuppressWarnings("UnusedDeclaration")
public class TwitchChatServerException extends Exception {
    public TwitchChatServerException(String message) {
        super(message);
    }

    public TwitchChatServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
