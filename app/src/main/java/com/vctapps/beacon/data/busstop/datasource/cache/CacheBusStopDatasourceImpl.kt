package com.vctapps.beacon.data.busstop.datasource.cache

import com.vctapps.beacon.core.InvalidData
import com.vctapps.beacon.data.busstop.datasource.LocalBusStopDatasource
import io.reactivex.Completable
import io.reactivex.Maybe

class CacheBusStopDatasourceImpl: LocalBusStopDatasource {

    companion object {
        fun getNewInstance(): LocalBusStopDatasource {
            return CacheBusStopDatasourceImpl()
        }
    }

    override fun isSetBusStopId(): Boolean {
        return CacheBusStop.busStopId != InvalidData.STRING
    }

    override fun getBusStopId(): Maybe<String> {
        return Maybe.create { emmiter ->
            if(isSetBusStopId()) emmiter.onSuccess(CacheBusStop.busStopId)

            emmiter.onComplete()
        }
    }

    override fun setBusStopId(busStopId: String) {
        CacheBusStop.busStopId = busStopId
    }

    override fun clean(): Completable {
        return Completable.create { emmiter ->
            CacheBusStop.busStopId = InvalidData.STRING

            emmiter.onComplete()
        }
    }
}