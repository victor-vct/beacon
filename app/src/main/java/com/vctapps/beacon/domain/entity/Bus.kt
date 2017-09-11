package com.vctapps.beacon.domain.entity

import java.io.Serializable
import java.util.*

data class Bus(val id: Int,
               val line: String,
               val origin: String,
               val destiny: String,
               var isFavorite: Boolean,
               val arrivalAt: Calendar): Serializable