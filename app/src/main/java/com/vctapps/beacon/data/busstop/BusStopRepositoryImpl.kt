package com.vctapps.beacon.data.busstop

import android.content.Context
import com.vctapps.beacon.data.busstop.datasource.beacon.BeaconDatasourceImplRemote
import com.vctapps.beacon.data.busstop.datasource.cache.CacheBusStopDatasourceImpl
import io.reactivex.Maybe
import org.altbeacon.beacon.BeaconConsumer

class BusStopRepositoryImpl(context: Context): BusStopRepository {

    val busstopDatasource = BeaconDatasourceImplRemote.getInstance(context)
    val localBusStopDatasource = CacheBusStopDatasourceImpl.getNewInstance()

    override fun setUp(beaconConsumer: BeaconConsumer) = busstopDatasource.bind(beaconConsumer)

    override fun getCloseBusStop(): Maybe<String> {
        return Maybe.concat(localBusStopDatasource.getBusStopId(),
                busstopDatasource.getCloseBusStop())
                .firstElement()
                .doOnSuccess { busStopId ->
                    if(!localBusStopDatasource.isSetBusStopId()) {
                        localBusStopDatasource.setBusStopId(busStopId)
                    }
                }
    }

    override fun close(beaconConsumer: BeaconConsumer) = busstopDatasource.unbind(beaconConsumer)

    override fun cleanCache() = localBusStopDatasource.clean()
}