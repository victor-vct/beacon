package com.vctapps.beacon.data.bus.datasource.remote.entity

import com.google.gson.annotations.SerializedName


class BusLine(@SerializedName("_id") val id: String,
              val busLine: String)