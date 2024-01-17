package com.keepcoding.dragonballavanzado.data.local

import com.keepcoding.dragonballavanzado.models.HeroLocal
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val preferencesDatabase: PreferencesDatabase,
    private val dao: HeroDAO
) {
    
    companion object {
        val TOKEN = "token_private"
    }

    fun getHeros() = this.dao.getAll()

    fun getHeroDetail(id: String) : HeroLocal {
        return dao.getHeroDetail(id)
    }
    
    fun postTogleFavorite(id: String) {
        dao.postTogleFavorite(id)
    }
    
    fun insertHeros(heros: List<HeroLocal>) {
        dao.insertAll(heros)
    }
    
    fun deleteAll() {
        dao.deleteAll()
    }
    
    fun getToken(): String? {
        return preferencesDatabase.getData(TOKEN)
    }
    
    fun setToken(value: String) {
        preferencesDatabase.setData(TOKEN, value)
    }
    
}