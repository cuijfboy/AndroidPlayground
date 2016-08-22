package name.ilab.playground.util;

import android.text.TextUtils;
import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.SimpleFormatter;

/**
 * Created by cuijfboy on 16/7/13.
 */
public class LoggerBuilder {

    private static final String TAG = LoggerBuilder.class.getName();
    private static final String ANDROID_HANDLER = "com.android.internal.logging.AndroidHandler";

    private String loggerName;
    private boolean isDebugApp = true;

    private boolean isLogFileEnable = false;
    private String logFileFolder;
    private String logFileName;
    private int logFileSizeLimitInBytes = 2 * 1024 * 1024; // 2MB
    private int logFileCount = 10;
    private boolean isLogFileAppend = true;

    // ------------------------------------------------------------------------

    public Logger build() {
        Log.d(TAG, "build() called with: " + toString());

        Logger logger = createLogger();

        replaceAndroidHandler(logger);

        setLogLevel(logger);

        if (isLogFileEnable) {
            initLogFileHandler(logger);
        }

        Log.d(TAG, "build() returned: " + logger);
        return logger;
    }

    private Logger createLogger() {
        if (TextUtils.isEmpty(loggerName)) {
            throw new IllegalArgumentException("Illegal logger getLoggerName : " + loggerName);
        }

        Logger logger = LoggerFactory.getLogger(loggerName);
        logger.info("Hello world ! I'm {} :)", loggerName);

        return logger;
    }

    private void replaceAndroidHandler(Logger logger) {
        java.util.logging.Logger rootJavaLogger = LogManager.getLogManager().getLogger("");
        for (java.util.logging.Handler handler : rootJavaLogger.getHandlers()) {
            if (ANDROID_HANDLER.equals(handler.getClass().getName())) {
                rootJavaLogger.removeHandler(handler);
                AndroidLoggingHandler newHandler = new AndroidLoggingHandler();
                rootJavaLogger.addHandler(newHandler);
                logger.info("replaceAndroidHandler : {} -> {}", handler, newHandler);
                return;
            }
        }
    }

    private void setLogLevel(Logger logger) {
        Level level = Level.INFO;
        if (isDebugApp) {
            level = Level.FINE;
        }
        getJavaLogger().setLevel(level);
        logger.info("LoggerBuilder.setLogLevel() : Set log level : {}, by setIsDebugApp : {}",
                level, isDebugApp);
    }

    private java.util.logging.Logger getJavaLogger() {
        return java.util.logging.Logger.getLogger(loggerName);
    }

    private void initLogFileHandler(Logger logger) {
        if (TextUtils.isEmpty(logFileFolder) || TextUtils.isEmpty(logFileName)) {
            throw new IllegalArgumentException(String.format(
                    "Illegal logFileFolder : %s or logFileName : %s, maybe null or empty !",
                    logFileFolder, logFileName));
        }

        if (!createLogFileFolder()) {
            logger.error("Could not create logFileFolder : {}, so no more log files !",
                    logFileFolder);
            return;
        }

        addFileHandler(logger);
    }

    private boolean createLogFileFolder() {
        File file = new File(logFileFolder);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return file.isDirectory();
    }

    private void addFileHandler(Logger logger) {
        String pattern = logFileFolder + File.separator + logFileName;

        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler(pattern,
                    logFileSizeLimitInBytes, logFileCount, isLogFileAppend);
        } catch (IOException e) {
            Log.e(TAG, "addFileHandler: Failed to create fileHandler !");
            e.printStackTrace();
            return;
        }

        fileHandler.setFormatter(new SimpleFormatter());
        getJavaLogger().addHandler(fileHandler);

        logger.info("LoggerBuilder.addFileHandler() : Added fileHandler with pattern : {}",
                pattern);
    }

    // ------------------------------------------------------------------------

    public String getLoggerName() {
        return loggerName;
    }

    public LoggerBuilder setLoggerName(String loggerName) {
        this.loggerName = loggerName;
        return this;
    }

    public boolean isDebugApp() {
        return isDebugApp;
    }

    public LoggerBuilder setIsDebugApp(boolean isDebugApp) {
        this.isDebugApp = isDebugApp;
        return this;
    }

    public boolean isLogFileEnable() {
        return isLogFileEnable;
    }

    public LoggerBuilder setIsLogFileEnable(boolean isLogFileEnable) {
        this.isLogFileEnable = isLogFileEnable;
        return this;
    }

    public String getLogFileFolder() {
        return logFileFolder;
    }

    public LoggerBuilder setLogFileFolder(String logFileFolder) {
        this.logFileFolder = logFileFolder;
        return this;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public LoggerBuilder setLogFileName(String logFileName) {
        this.logFileName = logFileName;
        return this;
    }

    public int getLogFileSizeLimitInBytes() {
        return logFileSizeLimitInBytes;
    }

    public LoggerBuilder setLogFileSizeLimitInBytes(int logFileSizeLimitInBytes) {
        this.logFileSizeLimitInBytes = logFileSizeLimitInBytes;
        return this;
    }

    public int getLogFileCount() {
        return logFileCount;
    }

    public LoggerBuilder setLogFileCount(int logFileCount) {
        this.logFileCount = logFileCount;
        return this;
    }

    public boolean isLogFileAppend() {
        return isLogFileAppend;
    }

    public LoggerBuilder setIsLogFileAppend(boolean isLogFileAppend) {
        this.isLogFileAppend = isLogFileAppend;
        return this;
    }

    @Override
    public String toString() {
        return "LoggerBuilder{" +
                "loggerName='" + loggerName + '\'' +
                ", isDebugApp=" + isDebugApp +
                ", isLogFileEnable=" + isLogFileEnable +
                ", logFileFolder='" + logFileFolder + '\'' +
                ", logFileName='" + logFileName + '\'' +
                ", logFileSizeLimitInBytes=" + logFileSizeLimitInBytes +
                ", logFileCount=" + logFileCount +
                ", isLogFileAppend=" + isLogFileAppend +
                '}';
    }

}
