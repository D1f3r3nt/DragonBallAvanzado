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

    @Json(name = "description")
    val description: String,
    
    @Json(name = "favorite")
    val favorite: Boolean,
)

fun HeroRemote.mapToLocal(): HeroLocal {
    return HeroLocal(this.id, this.name, this.photo, this.description, this.favorite)
}

fun HeroRemote.mapToUI(): HeroUI {
    return HeroUI(this.id, this.name, this.photo, this.description, this.favorite)
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

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean,
)

fun HeroLocal.mapToUI(): HeroUI {
    return HeroUI(this.id, this.name, this.photo, this.description, this.favorite)
}

data class HeroUI (
    val id: String,
    val name: String,
    val photo: String,
    val description: String,
    val favorite: Boolean,
)
