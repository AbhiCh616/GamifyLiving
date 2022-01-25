package com.example.gamifyliving.di

import android.content.Context
import androidx.room.Room
import com.example.gamifyliving.data.data_source.AppDatabase
import com.example.gamifyliving.data.data_source.RewardDAO
import com.example.gamifyliving.data.data_source.StatDao
import com.example.gamifyliving.data.data_source.TaskDao
import com.example.gamifyliving.data.repository.RewardRepositoryImpl
import com.example.gamifyliving.data.repository.StatRepositoryImpl
import com.example.gamifyliving.data.repository.TaskRepositoryImpl
import com.example.gamifyliving.domain.repository.RewardRepository
import com.example.gamifyliving.domain.repository.StatRepository
import com.example.gamifyliving.domain.repository.TaskRepository
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
    fun provideRewardDao(appDatabase: AppDatabase): RewardDAO {
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

}
