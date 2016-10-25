package name.ilab.playground;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import static name.ilab.playground.util.Log.logger;

//import name.ilab.playground.annotation.Playground;

/**
 * Created by cuijfboy on 16/8/22.
 */

//@Playground()
public class ThisApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        logger().info("ThisApp.onCreate()");
        LeakCanary.install(this);
    }

    @Override
    public Object getSystemService(String name) {
        return super.getSystemService(name);
    }
}
