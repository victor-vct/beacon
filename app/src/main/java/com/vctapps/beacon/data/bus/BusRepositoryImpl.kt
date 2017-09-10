package com.vctapps.beacon.data.bus

import com.vctapps.beacon.data.bus.datasource.remote.BusRemoteDatasource
import com.vctapps.beacon.domain.entity.Bus
import io.reactivex.Completable
import io.reactivex.Maybe
import java.util.*

class BusRepositoryImpl(val busRemoteDatasource: BusRemoteDatasource): BusRepository {

    override fun getBusList(busStopId: Int): Maybe<MutableList<Bus>> {
        return Maybe.just(getMockList())
    }

    override fun getBus(busStopId: Int, busId: Int): Maybe<Bus> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestBus(busStopId: Int, busId: Int) = busRemoteDatasource.requestBus(busId, busStopId)

    fun getMockList(): MutableList<Bus> {
        val mockList = mutableListOf<Bus>()

        mockList.add(getMockBus(0))
        mockList.add(getMockBus(1))
        mockList.add(getMockBus(2))
        mockList.add(getMockBus(3))

        return mockList
    }

    fun getMockBus(type: Int): Bus {
        when(type){
            0 -> return Bus(123, "B63", "Jardim Alvorada", "Jardim Alvorada", false, Calendar.getInstance())
            1 -> return Bus(123, "B47", "Terminal Santo André", "Vl Luzita", false, Calendar.getInstance())
            2 -> return Bus(123, "B13", "Jardim Aclimação", "Terminal Santo André", false, Calendar.getInstance())
            3 -> return Bus(123, "B19", "Jardim Aclimação", "Bairro Campestre", false, Calendar.getInstance())
        }

        return Bus(123, "B19", "Jardim Aclimação", "Bairro Campestre", false, Calendar.getInstance())
    }
}