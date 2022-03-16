package com.example.gamifyliving.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.domain.model.Task

@Database(
    version = 1,
    entities = [Stat::class, Task::class, Reward::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun statDao(): StatDao
    abstract fun taskDao(): TaskDao
    abstract fun rewardDao(): RewardDAO

}
