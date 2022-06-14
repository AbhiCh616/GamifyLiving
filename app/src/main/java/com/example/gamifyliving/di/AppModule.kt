package com.example.gamifyliving.di

import android.content.Context
import androidx.room.Room
import com.example.gamifyliving.data.data_source.local.AppDatabase
import com.example.gamifyliving.data.data_source.local.dao.*
import com.example.gamifyliving.data.repository.*
import com.example.gamifyliving.application.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDB(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "app_database"
        ).build()
    }

    @Provides
    fun provideRewardDao(appDatabase: AppDatabase): RewardDao {
        return appDatabase.rewardDao()
    }

    @Provides
    fun provideStatDao(appDatabase: AppDatabase): StatDao {
        return appDatabase.statDao()
    }

    @Provides
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }

    @Provides
    fun provideTodoDao(appDatabase: AppDatabase): TodoDao {
        return appDatabase.todoDao()
    }

    @Provides
    fun provideHabitDao(appDatabase: AppDatabase): HabitDao {
        return appDatabase.habitDao()
    }

    @Provides
    fun provideTodoScheduleDao(appDatabase: AppDatabase): TodoScheduleDao {
        return appDatabase.todoScheduleDao()
    }

    @Provides
    fun provideEverydayScheduleDao(appDatabase: AppDatabase): EverydayScheduleDao {
        return appDatabase.everydayScheduleDao()
    }

    @Provides
    fun provideRepeatScheduleDao(appDatabase: AppDatabase): RepeatScheduleDao {
        return appDatabase.repeatScheduleDao()
    }

    @Provides
    fun provideWeekDayScheduleDao(appDatabase: AppDatabase): WeekDayScheduleDao {
        return appDatabase.weekDayDao()
    }

    @Provides
    fun provideStoreItemDao(appDatabase: AppDatabase): StoreItemDao {
        return appDatabase.storeItemDao()
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindsModule {

    @Singleton
    @Binds
    abstract fun bindRewardRepository(
        rewardRepositoryImpl: RewardRepositoryImpl
    ): RewardRepository

    @Singleton
    @Binds
    abstract fun bindStatRepository(
        statRepositoryImpl: StatRepositoryImpl
    ): StatRepository

    @Singleton
    @Binds
    abstract fun bindTaskRepository(
        taskRepositoryImpl: TaskRepositoryImpl
    ): TaskRepository

    @Singleton
    @Binds
    abstract fun bindCoinRepository(
        coinRepositoryImpl: CoinRepositoryImpl
    ): CoinRepository

    @Singleton
    @Binds
    abstract fun bindStoreItemRepository(
        storeItemRepositoryImpl: StoreItemRepositoryImpl
    ): StoreItemRepository

}
