package com.vctapps.beacon.presentation.model

import java.io.Serializable

data class BusModelView(val id: String,
                        val name: String,
                        val destiny: String,
                        val origin: String,
                        val isFavorite: Boolean,
                        val arriveAt: String) : Serializable