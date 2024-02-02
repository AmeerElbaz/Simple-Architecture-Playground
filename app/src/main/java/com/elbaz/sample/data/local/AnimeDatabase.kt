package com.elbaz.sample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimeDbModel::class], exportSchema = false, version = 1)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun dao(): AnimeDao

//    companion object {
//        @Volatile
//        var INSTANCE: AnimeDatabase? = null
//        fun getDB(context: Context): AnimeDatabase {
//
//            return INSTANCE ?: synchronized(this) {
//
//                val instance = Room.databaseBuilder(
//                    context, AnimeDatabase::class.java,
//                    "AnimeDatabase"
//                ).build()
//                INSTANCE = instance
//                instance
//
//
//            }
//        }
//
//    }
}