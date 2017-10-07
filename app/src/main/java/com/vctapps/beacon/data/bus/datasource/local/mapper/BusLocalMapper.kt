package com.vctapps.beacon.data.bus.datasource.local.mapper

import com.vctapps.beacon.data.bus.datasource.local.entity.BusLocalEntity
import com.vctapps.beacon.domain.entity.Bus

object BusLocalMapper{

    fun transformFrom(bus: Bus): BusLocalEntity{
        return BusLocalEntity(bus.line)
    }

}