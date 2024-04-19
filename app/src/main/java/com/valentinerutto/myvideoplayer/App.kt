package com.valentinerutto.myvideoplayer

import android.app.Application
import com.valentinerutto.myvideoplayer.di.networkingModule
import com.valentinerutto.myvideoplayer.di.repositoryModule
import com.valentinerutto.myvideoplayer.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App : Application() {
    companion object {
        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        val modules = listOf(repositoryModule, networkingModule, viewmodelModule)

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)
            modules(modules)
        }

    }
}