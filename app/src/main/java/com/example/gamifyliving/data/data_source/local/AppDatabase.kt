package com.example.gamifyliving.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gamifyliving.data.data_source.local.converter.LocalDateConverter
import com.example.gamifyliving.data.data_source.local.converter.LocalTimeConverter
import com.example.gamifyliving.data.data_source.local.dao.RewardDao
import com.example.gamifyliving.data.data_source.local.dao.StatDao
import com.example.gamifyliving.data.data_source.local.dao.StoreItemDao
import com.example.gamifyliving.data.data_source.local.dao.TaskDao
import com.example.gamifyliving.data.data_source.local.model.*

@Database(
    version = 1,
    entities = [StatEntity::class, RewardEntity::class, StoreItemEntity::class, TaskEntity::class,
        TodoEntity::class, HabitEntity::class, TodoScheduleEntity::class, EverydayScheduleEntity::class,
        WeekDayScheduleEntity::class, RepeatAfterScheduleEntity::class],
    exportSchema = true
)
@TypeConverters(LocalDateConverter::class, LocalTimeConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun statDao(): StatDao
    abstract fun taskDao(): TaskDao
    abstract fun rewardDao(): RewardDao
    abstract fun storeItemDao(): StoreItemDao

}
