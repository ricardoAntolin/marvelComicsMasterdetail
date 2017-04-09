package com.rantolin.marvelcomics.presentation.internal.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.rantolin.marvelcomics.data.executor.JobExecutor
import com.rantolin.marvelcomics.data.repository.DataRepository
import com.rantolin.marvelcomics.domain.executor.PostExecutionThread
import com.rantolin.marvelcomics.domain.executor.ThreadExecutor
import com.rantolin.marvelcomics.domain.repository.Repository
import com.rantolin.marvelcomics.presentation.AndroidApplication
import com.rantolin.marvelcomics.presentation.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
class ApplicationModule(private val androidApplication: AndroidApplication) {

    @Provides
    @Singleton
    fun application(): AndroidApplication {
        return androidApplication
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return androidApplication
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return androidApplication.getSharedPreferences("app", Context.MODE_APPEND)
    }

    @Provides
    @Singleton
    fun provideRepository(dataRepository: DataRepository): Repository {
        return dataRepository

    }
}
