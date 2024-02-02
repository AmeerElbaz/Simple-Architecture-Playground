package com.elbaz.sample.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class AnimeDbModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val imageUrl: String,
    val rating: String,
    val category: Category,
)

enum class Category {
    RECENT, POPULAR, SOON
}