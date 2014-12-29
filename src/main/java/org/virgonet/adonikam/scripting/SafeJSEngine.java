package org.virgonet.adonikam.scripting;

import org.virgonet.adonikam.donnibot.interfaces.SafeJSEngineException;

public interface SafeJSEngine {
    String eval(String expression) throws SafeJSEngineException;
}
