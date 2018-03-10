package com.example.hugy.test36;

import android.app.Application;
import android.util.Log;



/**
 * Created by Woodslake on 2017/2/13.
 */

public class MainApplication extends Application {
    private final String TAG = MainApplication.class.getSimpleName();

    private static MainApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        application = this;
        if (BuildConfig.DEBUG) {
            CrashHandler.getInstance().init(this);
            CrashHandler.getInstance().sendPreviousReportsToServer();
        }
//        CookieManager.init();
//        AnalyticsManager.init(this);
//        //初始化log
//        KLog.init(BuildConfig.LOG_DEBUG);
    }

    public static MainApplication getApplication() {
        return application;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.i(TAG, "onLowMemory");
    }

}
