package com.keepcoding.dragonballavanzado.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class HeroRemote (
    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "photo")
    val photo: String,
)

fun HeroRemote.mapToLocal(): HeroLocal {
    return HeroLocal(this.id, this.name, this.photo)
}

fun HeroRemote.mapToUI(): HeroUI {
    return HeroUI(this.name, this.photo)
}

@Entity(tableName = "heros")
data class HeroLocal (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "photo")
    val photo: String,
)

fun HeroLocal.mapToUI(): HeroUI {
    return HeroUI(this.name, this.photo)
}

data class HeroUI (
    val name: String,
    val photo: String
)
