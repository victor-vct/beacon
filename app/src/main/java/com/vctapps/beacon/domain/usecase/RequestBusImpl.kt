package com.vctapps.beacon.domain.usecase

import com.vctapps.beacon.core.InvalidData
import com.vctapps.beacon.data.bus.BusRepository
import com.vctapps.beacon.data.busstop.BusStopRepository
import io.reactivex.Completable


class RequestBusImpl(val busStopRepository: BusStopRepository,
                     val busRepository: BusRepository): RequestBus {

    var idBus: Int = InvalidData.INT


    override fun setId(idBus: Int): RequestBus {
        this.idBus = idBus
        return this
    }

    override fun run(): Completable {
        return busStopRepository.getCloseBusStop()
                .flatMapCompletable { idBusStoṕ ->
                    busRepository.requestBus(idBus, idBusStoṕ.toInt())
                }
    }
}