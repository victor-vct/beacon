package com.vctapps.beacon.data.bus.datasource.remote.entity

import com.google.gson.annotations.SerializedName

class BusRemoteEntity(@SerializedName("_id") val id: String,
                      val busLine: BusLine,
                      val origin: String = "",
                      val destiny: String = "",
                      val arriveAt: String)