package org.virgonet.adonikam.scripting;

import net.datenwerke.sandbox.*;

import static net.datenwerke.sandbox.SandboxContext.AccessType;
import static net.datenwerke.sandbox.SandboxContext.RuntimeMode;

import net.datenwerke.sandbox.jvm.JvmFreelancer;
import net.datenwerke.sandbox.jvm.JvmPoolConfigImpl;
import net.datenwerke.sandbox.jvm.JvmPoolImpl;
import net.datenwerke.sandbox.jvm.exceptions.JvmKilledUnsafeThreadException;
import net.datenwerke.sandbox.jvm.exceptions.JvmServerDeadException;
import net.datenwerke.sandbox.jvm.exceptions.RemoteTaskExecutionFailed;
import net.datenwerke.sandbox.permissions.SecurityPermission;
import org.virgonet.adonikam.donnibot.interfaces.SafeJSEngineException;
//import sun.security.provider.PolicyFile;


import java.io.File;
import java.rmi.RemoteException;
//import java.security.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.PropertyPermission;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class SafeJSEngineImpl implements SafeJSEngine {
    private static final SandboxService service = new SandboxServiceImpl(true, new SandboxCleanupServiceImpl(), new JvmPoolImpl(new JvmPoolConfigImpl(1, 1, "-ea")));
//    static {
//        File policyFile = new File("src/main/resources", "all.policy");
//        Policy.setPolicy(new PolicyFile());
//        System.setProperty("java.security.policy", policyFile.getAbsolutePath());
//        System.setSecurityManager(new SecurityManager());
//    }
    @Override
    @SuppressWarnings("unchecked")
    public String eval(String expression) throws SafeJSEngineException {
        final SandboxContext context = new SandboxContext();
        context.addClassPermission(AccessType.PERMIT, "java.io.StringWriter");
        context.addClassPermission(AccessType.PERMIT, "java.io.PrintWriter");

        context.addClassForApplicationLoader("javax.script.ScriptEngineManager");
        context.addClassForApplicationLoader("javax.script.ScriptException");
        context.addClassForApplicationLoader("javax.script.ScriptEngine");

        context.addClassPermission(AccessType.PERMIT, "javax.script.ScriptEngineManager");
        context.addClassPermission(AccessType.PERMIT, "javax.script.ScriptException");
        context.addClassPermission(AccessType.PERMIT, "javax.script.ScriptEngine");

        context.addSecurityPermission(AccessType.PERMIT, new SecurityPermission("java.lang.RuntimePermission", "createClassLoader"));
        context.addSecurityPermission(AccessType.PERMIT, new SecurityPermission("java.lang.RuntimePermission", "getClassLoader"));
        context.addSecurityPermission(AccessType.PERMIT, new SecurityPermission("java.util.PropertyPermission", "line.separator", "read"));
        context.addSecurityPermission(AccessType.PERMIT, new SecurityPermission("java.lang.RuntimePermission", "accessDeclaredMembers"));
        context.addSecurityPermission(AccessType.PERMIT, new SecurityPermission("java.lang.reflect.ReflectPermission", "suppressAccessChecks"));
        context.addClassForSandboxLoader("java.lang.Throwable");

        //context.addSecurityPermission(AccessType.DENY, new SecurityPermission(""));
        context.setRunInThread(true);
        context.setRunRemote(true);
        //context.addClassForApplicationLoader("sun.reflect.");
        context.setMaximumRunTime(60, TimeUnit.SECONDS, RuntimeMode.CPU_TIME);
        context.setMaximumStackDepth(10000);

        SandboxedCallResult<String> stackTrace = null;
//        JvmFreelancer freelancer = null;
//
//        try {
//            freelancer = service.acquireFreelancer();
//        } catch (InterruptedException e) {
//            throw new SafeJSEngineException("Failed to execute JavaScript", e);
//        }
//
//        try {
//            freelancer.init(context);
//        } catch (RemoteException e) {
//            throw new SafeJSEngineException("Failed to execute JavaScript", e);
//        } catch (JvmServerDeadException e) {
//            throw new SafeJSEngineException("Failed to execute JavaScript", e);
//        }
//
//        try {
//            stackTrace = freelancer.runInContext(EvalEnvironment.class, expression);
//        } catch (JvmKilledUnsafeThreadException e) {
//            throw new SafeJSEngineException("Failed to execute JavaScript", e);
//        } catch (JvmServerDeadException e) {
//            throw new SafeJSEngineException("Failed to execute JavaScript", e);
//        } catch (RemoteTaskExecutionFailed e) {
//            throw new SafeJSEngineException("Failed to execute JavaScript", e);
//        } finally {
//            service.releaseFreelancer(freelancer);
//        }

        stackTrace = service.runSandboxed(EvalEnvironment.class, context, expression);

        return stackTrace.get();
    }
}
