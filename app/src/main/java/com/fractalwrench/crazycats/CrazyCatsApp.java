package com.fractalwrench.crazycats;

import android.app.Application;
import android.os.StrictMode;
import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.data.DataModule;
import com.fractalwrench.crazycats.injection.app.AppComponent;
import com.fractalwrench.crazycats.injection.app.AppModule;
import com.fractalwrench.crazycats.injection.app.DaggerAppComponent;
import com.fractalwrench.crazycats.injection.app.SchedulerModule;
import com.fractalwrench.crazycats.network.NetworkModule;
import com.squareup.leakcanary.LeakCanary;


public class CrazyCatsApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupStrictModePolicy();
        setupAppComponent();
        initialiseLeakCanary();
    }

    private void setupStrictModePolicy() { // log out any bad practices
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll()
                    .penaltyLog()
                    .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll()
                    .penaltyLog()
                    .build());
        }
    }

    private void initialiseLeakCanary() { // catch memory leaks
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void setupAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.DEBUG))
                .schedulerModule(new SchedulerModule())
                .dataModule(new DataModule())
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return appComponent;
    }

}