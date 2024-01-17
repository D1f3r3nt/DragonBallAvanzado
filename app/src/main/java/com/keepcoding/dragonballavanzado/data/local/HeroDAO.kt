package com.keepcoding.dragonballavanzado.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.keepcoding.dragonballavanzado.models.HeroLocal

@Dao
interface HeroDAO {

    @Query("SELECT * FROM heros")
    fun getAll(): List<HeroLocal>

    @Query("SELECT * FROM heros WHERE id = :id")
    fun getHeroDetail(id: String): HeroLocal

    @Query("UPDATE heros SET favorite = NOT favorite WHERE id = :id")
    fun postTogleFavorite(id: String)
    
    @Insert
    fun insertAll(heros: List<HeroLocal>)

    @Query("DELETE FROM heros")
    fun deleteAll()
}