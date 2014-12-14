package org.virgonet.adonikam.donnibot;

@SuppressWarnings("UnusedDeclaration")
public class BotException extends Exception {
    public BotException(String message) {
        super(message);
    }

    public BotException(String message, Throwable cause) {
        super(message, cause);
    }
}
