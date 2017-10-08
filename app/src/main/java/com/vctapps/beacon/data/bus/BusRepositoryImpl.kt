package com.vctapps.beacon.data.bus

import com.vctapps.beacon.data.bus.datasource.local.BusLocalDatasource
import com.vctapps.beacon.data.bus.datasource.remote.BusRemoteDatasource
import com.vctapps.beacon.data.bus.datasource.remote.BusRemoteMapper
import com.vctapps.beacon.data.bus.datasource.remote.entity.BusRequireResponse
import com.vctapps.beacon.domain.entity.Bus
import io.reactivex.Completable
import io.reactivex.Maybe
import java.util.*

class BusRepositoryImpl(private val busRemoteDatasource: BusRemoteDatasource,
                        private val busLocalDatasource: BusLocalDatasource): BusRepository {

    override fun getBusList(busStopId: Int): Maybe<MutableList<Bus>> {
        var busList = busRemoteDatasource.requestBusList(busStopId)
                .map(BusRemoteMapper::transform)
                .blockingGet()

        var favoriteIds = busLocalDatasource.getFavoriteLines().blockingGet()

        if(favoriteIds != null && favoriteIds.isNotEmpty()) {

            busList.forEach { bus -> if (favoriteIds.contains(bus.line)) bus.isFavorite = true }
        }

        return Maybe.just(busList)
    }

    override fun getBus(busStopId: Int, busId: Int): Maybe<Bus> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestBus(busStopId: Int, busModuleId: String): Completable {
        return Completable.create { emiter ->
            busRemoteDatasource
                    .requestBus(busModuleId, busStopId)
                    .doOnSuccess({ (id) ->
                        busLocalDatasource.saveBusRequire(id).blockingGet()
                        emiter.onComplete()})
                    .doOnComplete({emiter.onComplete()})
                    .doOnError({error -> emiter.onError(error)})
                    .blockingGet()
        }
    }

    override fun cancelRequestBus(): Completable {
        return Completable.create { emiter ->
            busLocalDatasource.getBusRequire()
                    .doOnSuccess({uid ->
                        busRemoteDatasource.cancelRequestBus(uid)
                                .doOnComplete({
                                    busLocalDatasource.deleteBusRequire().blockingGet()
                                    emiter.onComplete()
                                })
                                .doOnError({error -> emiter.onError(error)})
                                .blockingAwait()
                    })
                    .doOnError({error -> emiter.onError(error)})
                    .blockingGet()

        }
    }

    override fun checkIfThereIsRequest() = busLocalDatasource.getBusRequire()

    override fun saveFavoriteBus(bus: Bus) = busLocalDatasource.saveFavorite(bus)
    
}