package com.vctapps.beacon.presentation.listbus.model

data class BusModelView(val id: Int,
                        val name: String,
                        val destiny: String,
                        val isFavorite: Boolean,
                        val arriveAt: String)