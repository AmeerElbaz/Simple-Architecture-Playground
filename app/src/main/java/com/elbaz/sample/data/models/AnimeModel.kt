package com.elbaz.sample.data.models

import com.elbaz.sample.data.local.Category
import java.util.UUID

data class AnimeModel(

    val name: String,
    val imageUrl: String,
    val rating: String,
    val category: Category,
    val id: String = UUID.randomUUID().toString(),

    )
