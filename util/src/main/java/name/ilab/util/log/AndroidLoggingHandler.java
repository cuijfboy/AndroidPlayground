package name.ilab.util.log;

import android.util.Log;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by cuijfboy on 16/7/15.
 */
public class AndroidLoggingHandler extends Handler {

    private static final String TAG = AndroidLoggingHandler.class.getSimpleName();
    private static final int TAG_MAX_LENGTH = 30;

    @Override
    public void close() {
    }

    @Override
    public void flush() {
    }

    @SuppressWarnings({"WrongConstant", "ThrowableResultOfMethodCallIgnored"})
    @Override
    public void publish(LogRecord record) {
        if (!super.isLoggable(record)) {
            return;
        }

        String tag = getAndroidTag(record);
        int level = getAndroidLevel(record.getLevel());

        try {
            Log.println(level, tag, record.getMessage());
            if (record.getThrown() != null) {
                Log.println(level, tag, Log.getStackTraceString(record.getThrown()));
            }
        } catch (RuntimeException e) {
            Log.e(TAG, "Error logging message.", e);
        }
    }

    private String getAndroidTag(LogRecord record) {
        String name = record.getLoggerName();
        return name.length() > TAG_MAX_LENGTH ?
                name.substring(name.length() - TAG_MAX_LENGTH) : name;
    }

    private int getAndroidLevel(Level level) {
        int value = level.intValue();
        if (value >= 1000) {
            return Log.ERROR;
        } else if (value >= 900) {
            return Log.WARN;
        } else if (value >= 800) {
            return Log.INFO;
        } else {
            return Log.DEBUG;
        }
    }
}
