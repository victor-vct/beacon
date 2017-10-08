package com.vctapps.beacon.data.bus.datasource.remote

import com.vctapps.beacon.data.bus.datasource.remote.entity.BusRemoteEntity
import com.vctapps.beacon.domain.entity.Bus

object BusRemoteMapper {

    fun transform(busRemoteEntity: BusRemoteEntity): Bus{
        return Bus(busRemoteEntity.id,
                busRemoteEntity.busLine.busLine,
                busRemoteEntity.origin,
                busRemoteEntity.destiny,
                false,
                busRemoteEntity.arriveAt)
    }

    fun transform(busRemoteEntities: MutableList<BusRemoteEntity>): MutableList<Bus>{
        val busList = mutableListOf<Bus>()

        busRemoteEntities.forEach { busRemoteEntity ->
            busList.add(transform(busRemoteEntity))
        }

        return busList
    }
}