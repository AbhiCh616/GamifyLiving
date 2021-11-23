package com.example.gamifyliving

import android.app.Application
import com.example.gamifyliving.data.database.AppDatabase
import com.example.gamifyliving.repository.StatRepository

class GamifyLivingApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val statRepository by lazy { StatRepository(database.statDao()) }
}