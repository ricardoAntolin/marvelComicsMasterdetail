package com.rantolin.marvelcomics.presentation

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.rantolin.marvelcomics.presentation.internal.di.components.ApplicationComponent
import com.rantolin.marvelcomics.presentation.internal.di.components.DaggerApplicationComponent
import com.rantolin.marvelcomics.presentation.internal.di.modules.ApplicationModule
import com.squareup.leakcanary.LeakCanary
import io.realm.Realm
import io.realm.RealmConfiguration


class AndroidApplication : Application(), Application.ActivityLifecycleCallbacks {
    private var currentActivity: Activity? = null

    val component: ApplicationComponent
        get() = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

    override fun onCreate() {
        super.onCreate()
        this.initializeLeakDetection()
        component.inject(this)
        initRealm()
        registerActivityLifecycleCallbacks(this)
    }



    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }

    private fun initRealm() {
        Realm.init(this)
        val realmConfiguration: RealmConfiguration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    override fun onActivityPaused(p0: Activity?) {

    }

    override fun onActivityResumed(p0: Activity?) {
        currentActivity = p0
    }

    override fun onActivityStarted(p0: Activity?) {
        currentActivity = p0
    }

    override fun onActivityDestroyed(p0: Activity?) {

    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {

    }

    override fun onActivityStopped(p0: Activity?) {

    }

    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
        currentActivity = p0
    }

    fun getCurrentActivity(): Activity? {
        return currentActivity
    }
}