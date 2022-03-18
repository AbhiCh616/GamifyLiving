package com.example.gamifyliving.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gamifyliving.data.data_source.local.dao.RewardDao
import com.example.gamifyliving.data.data_source.local.dao.StatDao
import com.example.gamifyliving.data.data_source.local.dao.StoreItemDao
import com.example.gamifyliving.data.data_source.local.dao.TaskDao
import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.domain.model.StoreItem
import com.example.gamifyliving.domain.model.Task

@Database(
    version = 1,
    entities = [Stat::class, Task::class, Reward::class, StoreItem::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun statDao(): StatDao
    abstract fun taskDao(): TaskDao
    abstract fun rewardDao(): RewardDao
    abstract fun storeItemDao(): StoreItemDao

}
