package com.vctapps.beacon.data.busstop

import com.vctapps.beacon.data.busstop.datasource.LocalBusStopDatasource
import com.vctapps.beacon.data.busstop.datasource.RemoteBusstopDatasource
import io.reactivex.Maybe

class BusStopRepositoryImpl(val remoteBusstopDatasource: RemoteBusstopDatasource,
                            val localBusStopDatasource: LocalBusStopDatasource): BusStopRepository {

    override fun setUp() = remoteBusstopDatasource.bind()

    override fun getCloseBusStop(): Maybe<String> {
        return Maybe.concat(localBusStopDatasource.getBusStopId(),
                remoteBusstopDatasource.getCloseBusStop())
                .firstElement()
                .doOnSuccess { busStopId ->
                    if(!localBusStopDatasource.isSetBusStopId()) {
                        localBusStopDatasource.setBusStopId(busStopId)
                    }
                }
    }

    override fun close() = remoteBusstopDatasource.unbind()

    override fun cleanCache() = localBusStopDatasource.clean()
}