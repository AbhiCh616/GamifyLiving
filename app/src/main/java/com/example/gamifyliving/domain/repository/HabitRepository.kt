package com.example.gamifyliving.domain.repository

import com.example.gamifyliving.domain.model.entity.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    fun observe(): Flow<List<Habit>>
    suspend fun getById(id: Int): Habit?

    suspend fun add(habit: Habit)
    suspend fun update(habit: Habit)
    suspend fun delete(habit: Habit)

}
