package org.virgonet.adonikam.scripting;

import net.datenwerke.sandbox.SandboxedEnvironment;

import javax.script.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class EvalEnvironment implements SandboxedEnvironment<String> {

    private final String expression;

    final ScriptEngineManager engineManager = new ScriptEngineManager();
    final ScriptEngine scriptEngine = engineManager.getEngineByName("JavaScript");

    public EvalEnvironment(String expression) {
        this.expression = expression;
    }

    @Override
    public String execute() throws Exception {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            // does not work if using SecurityManager due to different class loaders
            //ScriptObjectMirror result = (ScriptObjectMirror) scriptEngine.eval(expression);
            String result = (String)scriptEngine.eval("load('src/main/resources/security.js');JSON.stringify(" + expression + ")");
            if (result == null)
                return "undefined";
            return result;
            //return (String)scriptEngine.eval("JSON.stringify("+expression+")");
        } catch (final Throwable e) {
            e.printStackTrace(pw);
        }
        return "JS error: " + sw.getBuffer().toString();
    }
}
