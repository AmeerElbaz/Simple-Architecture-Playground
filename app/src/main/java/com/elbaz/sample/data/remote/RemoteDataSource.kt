package com.elbaz.sample.data.remote

import com.elbaz.sample.data.models.AnimeModel
import com.elbaz.sample.data.local.Category
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor() {
    private val recentAnimeList = listOf(
        AnimeModel("Ore dake Level Up na Ken", "https://cdn.nekosapi.com/images/original/356e176c-ed05-40df-816d-faac26534b10.webp", "3",
            Category.RECENT),
        AnimeModel("Ore dake Level Up na Ken", "https://cdn.nekosapi.com/images/original/ce4b7573-d612-40cb-b915-e69f498f21fe.webp", "5.5",Category.RECENT),
        AnimeModel("Ore dake Level Up na Ken", "https://cdn.nekosapi.com/images/original/6e2fa7ae-cb25-4130-857e-d998958dc194.webp", "7",Category.RECENT),
        AnimeModel("Ore dake Level Up na Ken", "https://cdn.nekosapi.com/images/original/bdf520c1-4dda-4dfd-8a83-5cf61ace95b9.webp", "9",Category.RECENT),
    )
    private val mostPopularList = listOf(
        AnimeModel("Ore dake Level Up na Ken", "https://cdn.nekosapi.com/images/original/f4941912-8c7f-47b9-b5e0-d63ad352c6d0.webp", "6.2",Category.POPULAR),
        AnimeModel("Ore dake Level Up na Ken", "https://cdn.nekosapi.com/images/original/a859fe12-26e3-4f4f-ab75-f221b569c1a3.webp", "7.4",Category.POPULAR),
        AnimeModel("Ore dake Level Up na Ken", "https://cdn.nekosapi.com/images/original/2a6dad88-ea8d-40dd-af94-af7c6ea9dc06.webp", "8.2",Category.POPULAR),
        AnimeModel("Ore dake Level Up na Ken", "https://cdn.nekosapi.com/images/original/732c569a-478c-49c1-acea-f1d56e4aab71.webp", "8.1",Category.POPULAR),
    )
    private val comingSoonList = listOf(
        AnimeModel("Ore dake Level Up na Ken", "https://cdn.nekosapi.com/images/original/a21201da-9b7b-4089-a828-6c0ab3a03f60.webp", "3.4",Category.SOON),
        AnimeModel("Ore dake Level Up na Ken", "https://cdn.nekosapi.com/images/original/f4941912-8c7f-47b9-b5e0-d63ad352c6d0.webp", "4.7",Category.SOON),
        AnimeModel("Ore dake Level Up na Ken", "https://cdn.nekosapi.com/images/original/a21201da-9b7b-4089-a828-6c0ab3a03f60.webp", "5.7",Category.SOON),
        AnimeModel("Ore dake Level Up na Ken", "https://cdn.nekosapi.com/images/original/f4941912-8c7f-47b9-b5e0-d63ad352c6d0.webp", "3.6",Category.SOON),
    )
/*
*
* Assuming data is coming from different endpoints
*
* */
     suspend fun loadRecent(): List<AnimeModel> {
        delay(5000)
        return recentAnimeList.shuffled()
//         throw Exception("")
     }

     suspend fun loadMostPopular(): List<AnimeModel> {
        delay(5000)
        return mostPopularList.shuffled()
    }

     suspend fun loadComingSoon(): List<AnimeModel> {
        delay(5000)
        return comingSoonList.shuffled()
    }
}