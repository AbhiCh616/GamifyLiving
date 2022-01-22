package com.example.gamifyliving

import android.app.Application
import com.example.gamifyliving.data.data_source.AppDatabase
import com.example.gamifyliving.data.repository.RewardRepositoryImpl
import com.example.gamifyliving.data.repository.StatRepositoryImpl
import com.example.gamifyliving.data.repository.TaskRepositoryImpl

class GamifyLivingApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val statRepository by lazy { StatRepositoryImpl(database.statDao()) }
    val taskRepository by lazy { TaskRepositoryImpl(database.taskDao()) }
    val rewardRepository by lazy { RewardRepositoryImpl(database.rewardDao()) }
}