package com.example.gamifyliving.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.domain.model.Task

@Database(entities = [Stat::class, Task::class, Reward::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun statDao(): StatDao
    abstract fun taskDao(): TaskDao
    abstract fun rewardDao(): RewardDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}
