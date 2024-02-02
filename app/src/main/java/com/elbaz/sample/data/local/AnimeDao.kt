package com.elbaz.sample.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
@Dao
interface AnimeDao {

    @Upsert
    suspend fun saveAnime(animeList: List<AnimeDbModel>)

    @Query("SELECT * FROM AnimeDbModel")
    fun getAnimeList(): Flow<List<AnimeDbModel>>
}