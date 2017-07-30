package com.vctapps.beacon.data.datasource.busstop

import io.reactivex.Completable
import io.reactivex.Maybe
import org.altbeacon.beacon.BeaconConsumer

interface RemoteBusstopDatasource {

    fun bind(beaconConsumer: BeaconConsumer): Completable

    fun unbind(beaconConsumer: BeaconConsumer): Completable

    fun getCloseBusStop(): Maybe<String>

}