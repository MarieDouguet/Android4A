package com.esiea.android4a.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.esiea.android4a.data.local.models.UserLocal

@Database(
    entities = arrayOf(
        UserLocal::class
    ), version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun databaseDao(): DatabaseDao

}