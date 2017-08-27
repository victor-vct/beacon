package com.vctapps.beacon.data.busstop.datasource.beacon

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import io.reactivex.CompletableEmitter
import org.altbeacon.beacon.BeaconConsumer

class BeaconConsumerImpl(val application: Context, val emmiter: CompletableEmitter): BeaconConsumer {

    override fun getApplicationContext(): Context {
        return application.applicationContext
    }

    override fun unbindService(p0: ServiceConnection?) {
        application.unbindService(p0)
    }

    override fun bindService(p0: Intent?, p1: ServiceConnection?, p2: Int): Boolean {
        return application.bindService(p0, p1, p2)
    }

    override fun onBeaconServiceConnect() {
        emmiter.onComplete()
    }
}