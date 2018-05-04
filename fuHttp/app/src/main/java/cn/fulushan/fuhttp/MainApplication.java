package cn.fulushan.fuhttp;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by fulushan on 18/4/29.
 */

public class MainApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        installLeakCannary();

    }

    /**
     * 初始化安装LeakCannaey
     */
    private void installLeakCannary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        MainApplication application = (MainApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

}
