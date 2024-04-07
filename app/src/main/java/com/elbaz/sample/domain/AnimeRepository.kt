package com.elbaz.sample.domain

import com.elbaz.sample.data.models.AnimeModel
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    suspend fun updateAnimes()
    fun getAnimeList(): Flow<List<AnimeModel>>
}