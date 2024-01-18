package com.keepcoding.dragonballavanzado.data.local

import com.keepcoding.dragonballavanzado.models.HeroLocal

interface LocalDataSourceInterface {

    fun getHeros(): List<HeroLocal>

    fun getHeroDetail(id: String): HeroLocal

    fun postTogleFavorite(id: String)

    fun insertHeros(heros: List<HeroLocal>)

    fun deleteAll()

    fun getToken(): String?

    fun setToken(value: String)
}