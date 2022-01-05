package com.example.gamifyliving.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gamifyliving.data.model.Stat

@Database(entities = [Stat::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun statDao(): StatDao

    companion object {

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // drop old table
                database.execSQL("DROP TABLE Stat")
                // create new table
                database.execSQL("CREATE TABLE Stat (uid INTEGER, name TEXT, value INTEGER, PRIMARY KEY(uid) )")
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