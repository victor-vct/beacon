package com.vctapps.beacon.data.bus.datasource.local.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Index

@Entity
data class BusLocalEntity(@Index var line: String = ""){

    @Id var id: Long = 0

}