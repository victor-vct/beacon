package com.vctapps.beacon.data.bus.datasource.local.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class BusLocalEntity(@Id(assignable = true) var id: Long = 0)