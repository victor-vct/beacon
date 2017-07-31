package com.vctapps.beacon.presentation.model.mapper

import com.vctapps.beacon.domain.entity.Bus
import com.vctapps.beacon.presentation.model.BusModelView

object BusModelViewMapper {

    fun transformFrom(bus: Bus): BusModelView{
        return BusModelView(bus.id,
                bus.line,
                "Destino: " + bus.destiny,
                "Origem: " + bus.origin,
                bus.isFavorite,
                "3 min")
    }

    fun transformFrom(listBus: MutableList<Bus>): MutableList<BusModelView>{
        val listBusModelView = mutableListOf<BusModelView>()

        listBus.forEach { bus -> listBusModelView.add(transformFrom(bus)) }

        return listBusModelView
    }
}