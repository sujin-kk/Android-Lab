package com.example.siolab

import android.app.Application
import timber.log.Timber

class SioApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}