package com.elbaz.sample.data

import com.elbaz.sample.data.local.AnimeDao
import com.elbaz.sample.data.local.AnimeDbModel
import com.elbaz.sample.data.local.Category
import com.elbaz.sample.data.remote.RemoteDataSource
import com.elbaz.sample.data.models.AnimeModel
import com.elbaz.sample.data.models.toDbModel
import com.elbaz.sample.data.models.toUiModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepositoryImpl @Inject constructor (
    private val dataSource: RemoteDataSource ,
    private val dao: AnimeDao
) : AnimeRepository {
//    private val backgroundJob = CoroutineScope(Dispatchers.IO +  handler)

    override suspend fun updateAnimes() {
        coroutineScope {

            val recent = async { dataSource.loadRecent() }
            val comingSoon = async { dataSource.loadComingSoon() }
            val popular = async { dataSource.loadMostPopular() }

            saveLocally(
                (recent.await().toDbModel(Category.RECENT)
                        + comingSoon.await().toDbModel(Category.SOON)
                        + popular.await().toDbModel(Category.POPULAR))
            )
        }

    }

    override
    fun getAnimeList(): Flow<List<AnimeModel>> {

        return dao.getAnimeList().map { it.toUiModel() }

    }

    private suspend fun saveLocally(animeList: List<AnimeDbModel>) {


        dao.saveAnime(animeList)
    }

}