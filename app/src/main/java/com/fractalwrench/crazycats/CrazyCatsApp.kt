package com.fractalwrench.crazycats

import android.app.Application
import android.os.StrictMode

import com.fractalwrench.crazycats.data.DataModule
import com.fractalwrench.crazycats.injection.app.AppComponent
import com.fractalwrench.crazycats.injection.app.AppModule
import com.fractalwrench.crazycats.injection.app.DaggerAppComponent
import com.fractalwrench.crazycats.injection.app.SchedulerModule
import com.fractalwrench.crazycats.network.NetworkModule
import com.squareup.leakcanary.LeakCanary


class CrazyCatsApp : Application() {

    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        setupStrictModePolicy()
        setupAppComponent()
        initialiseLeakCanary()
    }

    private fun setupStrictModePolicy() { // log out any bad practices
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectAll()
                    .penaltyLog()
                    .build())

            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().detectAll()
                    .penaltyLog()
                    .build())
        }
    }

    private fun initialiseLeakCanary() { // catch memory leaks
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    private fun setupAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(BuildConfig.DEBUG))
                .schedulerModule(SchedulerModule())
                .dataModule(DataModule())
                .build()
    }

}
