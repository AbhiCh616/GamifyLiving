package com.example.gamifyliving

import android.app.Application
import com.example.gamifyliving.data.database.AppDatabase
import com.example.gamifyliving.repository.StatRepository
import com.example.gamifyliving.repository.TaskRepository

class GamifyLivingApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val statRepository by lazy { StatRepository(database.statDao()) }
    val taskRepository by lazy { TaskRepository(database.taskDao()) }
}