package com.elbaz.sample.di

import android.content.Context
import androidx.room.Room
import com.elbaz.sample.domain.AnimeRepository
import com.elbaz.sample.data.AnimeRepositoryImpl
import com.elbaz.sample.data.local.AnimeDao
import com.elbaz.sample.data.local.AnimeDatabase
import com.elbaz.sample.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideRepo(repository: AnimeRepositoryImpl): AnimeRepository

}


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
     fun provideRemoteDataSource(): RemoteDataSource  = RemoteDataSource()

}


@Module
@InstallIn(SingletonComponent::class)
object  DatabaseModule {
    @Singleton
    @Provides

    fun provideDatabase(@ApplicationContext context: Context): AnimeDatabase =
        Room.databaseBuilder(context, AnimeDatabase::class.java, "Anime.db").build()
    @Singleton
    @Provides
    fun provideDao(database: AnimeDatabase): AnimeDao = database.dao()

}

