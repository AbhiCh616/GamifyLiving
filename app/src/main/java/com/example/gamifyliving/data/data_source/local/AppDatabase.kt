package com.example.gamifyliving.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gamifyliving.data.data_source.local.dao.RewardDao
import com.example.gamifyliving.data.data_source.local.dao.StatDao
import com.example.gamifyliving.data.data_source.local.dao.StoreItemDao
import com.example.gamifyliving.data.data_source.local.dao.TaskDao
import com.example.gamifyliving.data.data_source.local.model.RewardEntity
import com.example.gamifyliving.data.data_source.local.model.StatEntity
import com.example.gamifyliving.data.data_source.local.model.StoreItemEntity
import com.example.gamifyliving.data.data_source.local.model.TaskEntity

@Database(
    version = 1,
    entities = [StatEntity::class, TaskEntity::class, RewardEntity::class, StoreItemEntity::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun statDao(): StatDao
    abstract fun taskDao(): TaskDao
    abstract fun rewardDao(): RewardDao
    abstract fun storeItemDao(): StoreItemDao

}
