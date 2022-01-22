package com.example.gamifyliving

import android.app.Application
import com.example.gamifyliving.data.data_source.AppDatabase
import com.example.gamifyliving.data.repository.RewardRepositoryImpl
import com.example.gamifyliving.data.repository.StatRepositoryImpl
import com.example.gamifyliving.data.repository.TaskRepositoryImpl
import com.example.gamifyliving.presentation.edit_task.EditTaskViewModelFactory

class GamifyLivingApplication : Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    val statRepository by lazy { StatRepositoryImpl(database.statDao()) }
    val taskRepository by lazy { TaskRepositoryImpl(database.taskDao()) }
    val rewardRepository by lazy { RewardRepositoryImpl(database.rewardDao()) }
    val editTaskViewModelFactory by lazy { EditTaskViewModelFactory(taskRepository = taskRepository) }
}