package io.mindbend.discoveo;

import android.app.Application;
import android.os.Build;
import android.util.Log;

/**
 * Created by rohitsharma on 2015-08-15.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            isLollipopOrHigher = true;
            Log.wtf("App", "is Lollipop or higher");
        }
    }

    public static boolean isLollipopOrHigher = false;
}
