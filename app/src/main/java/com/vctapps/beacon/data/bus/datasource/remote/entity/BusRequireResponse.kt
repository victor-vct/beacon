package com.vctapps.beacon.data.bus.datasource.remote.entity

import com.google.gson.annotations.SerializedName


data class BusRequireResponse(@SerializedName("_id") val id: String,
                              @SerializedName("busModule") val busModuleId: String,
                              @SerializedName("busStop") val BusStopId: String)