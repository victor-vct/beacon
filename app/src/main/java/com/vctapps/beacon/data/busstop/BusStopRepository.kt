package com.vctapps.beacon.data.busstop

import io.reactivex.Completable
import io.reactivex.Maybe
import org.altbeacon.beacon.BeaconConsumer

interface BusStopRepository {

    fun setUp(beaconConsumer: BeaconConsumer): Completable

    fun getCloseBusStop(): Maybe<String>

    fun close(beaconConsumer: BeaconConsumer): Completable

    fun cleanCache(): Completable

}