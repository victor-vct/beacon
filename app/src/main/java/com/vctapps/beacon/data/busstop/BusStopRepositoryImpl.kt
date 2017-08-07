package com.vctapps.beacon.data.busstop

import com.vctapps.beacon.data.busstop.datasource.LocalBusStopDatasource
import com.vctapps.beacon.data.busstop.datasource.RemoteBusstopDatasource
import io.reactivex.Maybe
import org.altbeacon.beacon.BeaconConsumer

class BusStopRepositoryImpl(val remoteBusstopDatasource: RemoteBusstopDatasource,
                            val localBusStopDatasource: LocalBusStopDatasource): BusStopRepository {

    override fun setUp(beaconConsumer: BeaconConsumer) = remoteBusstopDatasource.bind(beaconConsumer)

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

    override fun close(beaconConsumer: BeaconConsumer) = remoteBusstopDatasource.unbind(beaconConsumer)

    override fun cleanCache() = localBusStopDatasource.clean()
}