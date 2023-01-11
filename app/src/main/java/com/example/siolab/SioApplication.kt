package com.example.siolab

import android.app.Application
import com.example.siolab.common.timber.TimberDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SioApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // init
        initTimber()
    }

    private fun initTimber() {
        Timber.plant(TimberDebugTree())
    }
}