package com.vctapps.beacon.data.datasource.busstop

import io.reactivex.Completable
import io.reactivex.Maybe

interface LocalBusStopDatasource {

    fun isSetBusStopId(): Boolean

    fun getBusStopId(): Maybe<String>

    fun setBusStopId(busStopId: String)

    fun clean(): Completable

}