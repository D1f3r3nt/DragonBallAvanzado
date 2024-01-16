package com.keepcoding.dragonballavanzado.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keepcoding.dragonballavanzado.data.local.HeroDAO
import com.keepcoding.dragonballavanzado.models.HeroLocal

@Database(entities = [HeroLocal::class], version = 1)
abstract class HeroDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDAO
}