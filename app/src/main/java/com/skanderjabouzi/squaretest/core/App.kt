package com.skanderjabouzi.squaretest.core

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import com.skanderjabouzi.squaretest.di.component.AppComponent
import com.skanderjabouzi.squaretest.di.component.DaggerAppComponent
import io.github.asvid.firstlib.FirstLibObject
import okhttp3.OkHttpClient

open class App: Application(), ImageLoaderFactory {

    val appComponent: AppComponent by lazy { initDagger() }

    private fun initDagger(): AppComponent {
        return DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        println(FirstLibObject.someMethd())
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .crossfade(true)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(applicationContext))
                    .build()
            }
            .build()
    }

    open fun getBaseUrl() = "https://s3.amazonaws.com/sq-mobile-interview/"

    companion object {
        lateinit var INSTANCE: App
            private set
    }
}