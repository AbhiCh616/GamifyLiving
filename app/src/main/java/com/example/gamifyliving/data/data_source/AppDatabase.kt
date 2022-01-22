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

@Database(entities = [Stat::class, Task::class, Reward::class], version = 4, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun statDao(): StatDao
    abstract fun taskDao(): TaskDao
    abstract fun rewardDao(): RewardDAO

    companion object {

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // drop old table
                database.execSQL("DROP TABLE Stat")
                // create new table
                database.execSQL("CREATE TABLE Stat (uid INTEGER, name TEXT, value INTEGER, PRIMARY KEY(uid) )")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE Task (uid INTEGER, name TEXT, status INTEGER, PRIMARY KEY(uid))")
            }
        }

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE Reward (uid INTEGER, taskId INTEGER REFERENCES Task (uid), statId INTEGER REFERENCES Stat (uid), points INTEGER, PRIMARY KEY(uid))")
            }
        }

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
