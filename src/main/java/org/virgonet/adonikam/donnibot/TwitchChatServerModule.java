package org.virgonet.adonikam.donnibot;

import com.google.inject.AbstractModule;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.expression.DefaultResolver;
import org.apache.commons.beanutils.expression.Resolver;
import org.virgonet.adonikam.donnibot.interfaces.TwitchChatServer;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class TwitchChatServerModule extends AbstractModule {
    private static final String FILE1 = "config/defaults.properties";
    private static final String FILE2 = "config/donnibot.properties";
    private final BotConfig botConfig = getBotConfigFromFiles(FILE1, FILE2);

    @Override
    protected void configure() {
        bind(BotConfig.class).toInstance(botConfig);
        bind(TwitchChatServer.class).to(TwitchChatServerImpl.class);
    }


    private BotConfig getBotConfigFromFiles(String... configFileNames) {
        boolean foundOne = false;
        final BotConfig botConfig = new BotConfig();
        Properties p = new Properties();
        try {
            for (String fileName : configFileNames) {
                File file = new File(fileName);
                if (file.exists()) {
                    foundOne = true;
                    p.load(new FileInputStream(file));
                }
            }
            if (!foundOne)
                throw new RuntimeException("No configuration files found.");
            for(String x : p.stringPropertyNames()) {
                String x1 = x;
                Resolver r = new DefaultResolver();
                while (r.hasNested(x1)) {
                    x1 = r.remove(x1);
                }
                BeanInfo info = Introspector.getBeanInfo(botConfig.getClass());
                PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
                for (PropertyDescriptor o : descriptors)
                    if (o.getName().equals(x1)) {
                        Object z = ConvertUtils.convert(p.get(x), o.getPropertyType());
                        o.getWriteMethod().invoke(botConfig, z);
                        break;
                    }
            }
        } catch (IOException
                | IllegalAccessException
                | InvocationTargetException
                | IntrospectionException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
        return botConfig;
    }
}
