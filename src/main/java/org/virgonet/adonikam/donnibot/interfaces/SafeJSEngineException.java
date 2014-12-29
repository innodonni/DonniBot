package org.virgonet.adonikam.donnibot.interfaces;

@SuppressWarnings("UnusedDeclaration")
public class SafeJSEngineException extends Exception {
    public SafeJSEngineException(String message) {
        super(message);
    }

    public SafeJSEngineException(String message, Throwable cause) {
        super(message, cause);
    }
}
