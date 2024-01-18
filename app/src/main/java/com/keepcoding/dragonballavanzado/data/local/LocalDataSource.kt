package com.keepcoding.dragonballavanzado.data.local

import com.keepcoding.dragonballavanzado.models.HeroLocal
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val preferencesDatabase: PreferencesDatabase,
    private val dao: HeroDAO
) : LocalDataSourceInterface {
    
    companion object {
        val TOKEN = "token_private"
    }

    override fun getHeros() = this.dao.getAll()

    override fun getHeroDetail(id: String) : HeroLocal {
        return dao.getHeroDetail(id)
    }

    override fun postTogleFavorite(id: String) {
        dao.postTogleFavorite(id)
    }

    override fun insertHeros(heros: List<HeroLocal>) {
        dao.insertAll(heros)
    }

    override fun deleteAll() {
        dao.deleteAll()
    }

    override fun getToken(): String? {
        return preferencesDatabase.getData(TOKEN)
    }

    override fun setToken(value: String) {
        preferencesDatabase.setData(TOKEN, value)
    }
    
}