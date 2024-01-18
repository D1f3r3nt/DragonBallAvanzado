package com.keepcoding.andoird_class_one.utils

import com.keepcoding.dragonballavanzado.models.HeroLocal
import com.keepcoding.dragonballavanzado.models.HeroRemote

fun generateLocalHeroes(size: Int) = (1..size).map { HeroLocal("id$it", "name$it", "photo$it", "description$it", false) }

fun generateRemoteHeroes(size: Int) = (1..size).map { HeroRemote("id$it", "name$it", "photo$it", "description$it", false) }