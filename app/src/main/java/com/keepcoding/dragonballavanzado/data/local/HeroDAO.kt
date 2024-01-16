package com.keepcoding.dragonballavanzado.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.keepcoding.dragonballavanzado.models.HeroLocal

@Dao
interface HeroDAO {

    @Query("SELECT * FROM heros")
    fun getAll(): List<HeroLocal>

    @Insert
    fun insertAll(heros: List<HeroLocal>)
}