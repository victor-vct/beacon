package com.vctapps.beacon.core

import android.app.Application
import com.vctapps.beacon.BuildConfig
import com.vctapps.beacon.di.BeaconComponent
import com.vctapps.beacon.di.BeaconModule
import com.vctapps.beacon.di.DaggerBeaconComponent
import timber.log.Timber


class BeaconApplication: Application() {

    lateinit var beaconComponent: BeaconComponent

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        beaconComponent = initDagger()
    }

    fun initDagger() = DaggerBeaconComponent.builder()
            .beaconModule(BeaconModule(this))
            .build()
}