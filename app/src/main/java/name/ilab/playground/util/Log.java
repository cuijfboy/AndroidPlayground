package name.ilab.playground.util;

import org.slf4j.Logger;

import name.ilab.playground.BuildConfig;
import name.ilab.util.log.LoggerBuilder;

/**
 * Created by cuijfboy on 16/7/27.
 */
public class Log {

    private static final Logger logger = new LoggerBuilder()
            .setLoggerName("[AndroidPlayground]")
            .setIsDebugApp(BuildConfig.DEBUG)
            .build();

    public static Logger logger() {
        return logger;
    }
}
