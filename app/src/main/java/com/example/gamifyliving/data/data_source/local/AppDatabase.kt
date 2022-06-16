package com.example.gamifyliving.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gamifyliving.data.data_source.local.converter.LocalDateConverter
import com.example.gamifyliving.data.data_source.local.converter.LocalTimeConverter
import com.example.gamifyliving.data.data_source.local.dao.*
import com.example.gamifyliving.data.data_source.local.model.*

@Database(
    version = 1,
    entities = [StatEntity::class, RewardEntity::class, StoreItemEntity::class, TaskEntity::class,
        TodoEntity::class, HabitEntity::class, DateScheduleEntity::class, EverydayScheduleEntity::class,
        WeekDayScheduleEntity::class, RepeatScheduleEntity::class],
    exportSchema = true
)
@TypeConverters(LocalDateConverter::class, LocalTimeConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun statDao(): StatDao
    abstract fun rewardDao(): RewardDao
    abstract fun storeItemDao(): StoreItemDao
    abstract fun taskDao(): TaskDao
    abstract fun todoDao(): TodoDao
    abstract fun habitDao(): HabitDao
    abstract fun todoScheduleDao(): TodoScheduleDao
    abstract fun everydayScheduleDao(): EverydayScheduleDao
    abstract fun repeatScheduleDao(): RepeatScheduleDao
    abstract fun weekDayDao(): WeekDayScheduleDao

}
