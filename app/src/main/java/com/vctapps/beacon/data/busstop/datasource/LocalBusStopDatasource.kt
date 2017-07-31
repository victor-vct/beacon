package com.vctapps.beacon.data.busstop.datasource

import io.reactivex.Completable
import io.reactivex.Maybe

interface LocalBusStopDatasource {

    fun isSetBusStopId(): Boolean

    fun getBusStopId(): Maybe<String>

    fun setBusStopId(busStopId: String)

    fun clean(): Completable

}