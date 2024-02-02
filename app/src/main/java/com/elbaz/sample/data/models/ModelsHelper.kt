package com.elbaz.sample.data.models

import com.elbaz.sample.data.local.AnimeDbModel
import com.elbaz.sample.data.local.Category

fun List<AnimeModel>.toDbModel(category: Category): List<AnimeDbModel> {
    return this.map {
        AnimeDbModel(it.id, it.name, it.imageUrl, it.rating, category)
    }

}
fun List<AnimeDbModel>.toUiModel(): List<AnimeModel> {
    return this.map {
        AnimeModel( it.name, it.imageUrl, it.rating,it.category,it.id)
    }

}