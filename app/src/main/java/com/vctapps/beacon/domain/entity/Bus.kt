package com.vctapps.beacon.domain.entity

import java.io.Serializable
import java.util.*

data class Bus(val id: String,
               val line: String,
               val origin: String?,
               val destiny: String?,
               var isFavorite: Boolean,
               val arriveAt: String): Serializable