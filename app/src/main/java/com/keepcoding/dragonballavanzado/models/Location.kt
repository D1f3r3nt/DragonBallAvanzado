package com.keepcoding.dragonballavanzado.models

import com.google.android.gms.maps.model.LatLng
import com.squareup.moshi.Json

data class LocationRemote (
    @Json(name = "id")
    val id: String,
    
    @Json(name = "dateShow")
    val dateShow: String,

    @Json(name = "latitud")
    val latitud: String,

    @Json(name = "longitud")
    val longitud: String,
)

fun LocationRemote.mapToUI(): LocationUI {
    return LocationUI(this.dateShow, this.latitud, this.longitud)
}

data class LocationUI (
    val dateShow: String,
    val latitud: String,
    val longitud: String,
)

fun LocationUI.getLatLng(): LatLng {
    return LatLng(this.latitud.toDouble(), this.longitud.toDouble())
}