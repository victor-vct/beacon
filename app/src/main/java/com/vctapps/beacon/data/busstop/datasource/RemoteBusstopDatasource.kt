package com.vctapps.beacon.data.busstop.datasource

import io.reactivex.Completable
import io.reactivex.Maybe
import org.altbeacon.beacon.BeaconConsumer

interface RemoteBusstopDatasource {

    fun bind(beaconConsumer: BeaconConsumer): Completable

    fun unbind(beaconConsumer: BeaconConsumer): Completable

    fun getCloseBusStop(): Maybe<String>

}