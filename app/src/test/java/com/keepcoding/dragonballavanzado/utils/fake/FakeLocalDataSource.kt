package com.keepcoding.dragonballavanzado.utils.fake

import com.keepcoding.dragonballavanzado.data.local.LocalDataSource
import com.keepcoding.dragonballavanzado.data.local.LocalDataSourceInterface
import com.keepcoding.dragonballavanzado.models.HeroLocal

class FakeLocalDataSource : LocalDataSourceInterface {

    private var database = mutableListOf<HeroLocal>()
    private val sharedPreferences = mutableMapOf<String, String>(Pair(LocalDataSource.TOKEN, ""))

    override fun getHeros(): List<HeroLocal> {
        return database
    }

    override fun getHeroDetail(id: String): HeroLocal {
        return database.find { it.id == id } ?: HeroLocal("", "", "", "", false)
    }

    override fun postTogleFavorite(id: String) {
        database = database.map {
            if (it.id == id) {
                it.favorite = !it.favorite
            }
            it
        }.toMutableList()
    }

    override fun insertHeros(heros: List<HeroLocal>) {
        database.addAll(heros)
    }

    override fun deleteAll() {
        database.clear()
    }

    override fun getToken(): String? {
        return sharedPreferences.get(LocalDataSource.TOKEN)
    }

    override fun setToken(value: String) {
        sharedPreferences.set(LocalDataSource.TOKEN, value)
    }

}