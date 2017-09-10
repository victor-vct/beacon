package com.vctapps.beacon.data.bus.datasource.remote.entity

import java.util.*

class BusRemoteEntity(val id: Int,
                      val line: String,
                      val origin: String,
                      val destiny: String,
                      var isFavorite: Boolean,
                      val arrivalAt: Calendar)