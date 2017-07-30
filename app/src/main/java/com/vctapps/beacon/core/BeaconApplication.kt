package com.vctapps.beacon.core

import android.app.Application
import com.vctapps.beacon.BuildConfig
import timber.log.Timber


class BeaconApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}