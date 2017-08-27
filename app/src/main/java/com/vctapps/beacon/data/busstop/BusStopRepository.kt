package com.vctapps.beacon.data.busstop

import io.reactivex.Completable
import io.reactivex.Maybe

interface BusStopRepository {

    fun setUp(): Completable

    fun getCloseBusStop(): Maybe<String>

    fun close(): Completable

    fun cleanCache(): Completable

}