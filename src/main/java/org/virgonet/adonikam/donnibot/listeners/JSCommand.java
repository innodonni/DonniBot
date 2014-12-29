package org.virgonet.adonikam.donnibot.listeners;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.virgonet.adonikam.donnibot.interfaces.SafeJSEngineException;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServerListener;
import org.virgonet.adonikam.scripting.SafeJSEngine;

public class JSCommand implements TwitchChatServerListener {
    private static Logger LOGGER = LoggerFactory.getLogger(JSCommand.class);

    private SafeJSEngine safeJSEngine;

    @Inject
    public JSCommand(SafeJSEngine safeJSEngine) {

        this.safeJSEngine = safeJSEngine;
    }

    @Override
    public String processCommand(String command, String... args) {
        try {
            return safeJSEngine.eval(String.join(" ", args));
        } catch (SafeJSEngineException e) {
            LOGGER.error("Failed to execute JS command", e);
            return null;
        }
    }

}
