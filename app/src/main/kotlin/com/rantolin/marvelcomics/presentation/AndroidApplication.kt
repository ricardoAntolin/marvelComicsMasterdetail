package com.rantolin.marvelcomics.presentation

import android.app.Application
import com.rantolin.marvelcomics.presentation.internal.di.components.ApplicationComponent
import com.rantolin.marvelcomics.presentation.internal.di.components.DaggerApplicationComponent
import com.rantolin.marvelcomics.presentation.internal.di.modules.ApplicationModule
import com.squareup.leakcanary.LeakCanary
import io.realm.Realm
import io.realm.RealmConfiguration


class AndroidApplication : Application() {

    val component: ApplicationComponent
        get() = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

    override fun onCreate() {
        super.onCreate()
        this.initializeLeakDetection()
        component.inject(this)
        initRealm()
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
}