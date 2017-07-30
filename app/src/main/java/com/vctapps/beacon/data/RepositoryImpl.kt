package com.vctapps.beacon.data

import android.content.Context
import com.vctapps.beacon.data.datasource.busstop.beacon.BeaconDatasourceImplRemote
import com.vctapps.beacon.data.datasource.busstop.cache.CacheBusStopDatasourceImpl
import io.reactivex.Maybe
import org.altbeacon.beacon.BeaconConsumer

class RepositoryImpl(context: Context): Repository {

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