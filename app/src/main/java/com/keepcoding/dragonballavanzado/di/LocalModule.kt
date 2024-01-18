package com.keepcoding.dragonballavanzado.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.keepcoding.dragonballavanzado.data.local.HeroDAO
import com.keepcoding.dragonballavanzado.data.local.LocalDataSource
import com.keepcoding.dragonballavanzado.data.local.LocalDataSourceInterface
import com.keepcoding.dragonballavanzado.data.local.database.HeroDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    private val FILE_PREFERENCE = "global_data"

    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(FILE_PREFERENCE, Context.MODE_PRIVATE)
    }

    @Provides
    fun providesHeroDatabase(@ApplicationContext context: Context): HeroDatabase {
        return Room.databaseBuilder(
            context,
            HeroDatabase::class.java,
            "hero-database"
        ).build()
    }

    @Provides
    fun providesHeroDao(db: HeroDatabase): HeroDAO {
        return db.heroDao()
    }

    @Provides
    fun providesLocalDataSourceInterface(localDataSource: LocalDataSource): LocalDataSourceInterface {
        return localDataSource
    }
}