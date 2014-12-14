package org.virgonet.adonikam.donnibot;

import org.pircbotx.exception.IrcException;

public class BotException extends Exception {
    public BotException(String message) {
        super(message);
    }

    public BotException(String message, Throwable cause) {
        super(message, cause);
    }
}
