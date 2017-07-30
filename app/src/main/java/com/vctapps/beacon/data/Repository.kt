package com.vctapps.beacon.data

import io.reactivex.Completable
import io.reactivex.Maybe
import org.altbeacon.beacon.BeaconConsumer

interface Repository {

    fun setUp(beaconConsumer: BeaconConsumer): Completable

    fun getCloseBusStop(): Maybe<String>

    fun close(beaconConsumer: BeaconConsumer): Completable

    fun cleanCache(): Completable

}