package com.example.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}

