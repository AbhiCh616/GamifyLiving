package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.DateScheduleDao
import com.example.gamifyliving.data.data_source.local.dao.RewardDao
import com.example.gamifyliving.data.data_source.local.dao.TaskDao
import com.example.gamifyliving.data.data_source.local.dao.TodoDao
import com.example.gamifyliving.data.data_source.local.mapper.toDataModel
import com.example.gamifyliving.data.data_source.local.mapper.toTaskEntity
import com.example.gamifyliving.data.data_source.local.mapper.toTodoModel
import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
    private val todoDao: TodoDao,
    private val dateScheduleDao: DateScheduleDao,
    private val rewardDao: RewardDao,
) : TodoRepository {

    override fun observe(): Flow<List<Todo>> = taskDao.getAll().map {
        it.toTodoModel()
    }

    override suspend fun getById(id: Int): Todo? =
        taskDao.getTaskWithDetails(id = id)?.toTodoModel()

    override suspend fun add(todo: Todo) {

        val taskEntity = todo.toTaskEntity()
        taskDao.insert(taskEntity = taskEntity)

        val todoEntity = todo.toDataModel()
        todoDao.insert(todoEntity)

        todo.schedule?.let { dateSchedule ->
            val dateScheduleEntity = dateSchedule.toDataModel(todoId = todo.id)
            dateScheduleDao.insert(dateScheduleEntity)
        }

        todo.rewards?.let { rewards ->
            val rewardsEntity = rewards.toDataModel(taskId = todo.id)
            rewardDao.insert(rewardsEntity)
        }

    }

    override suspend fun update(todo: Todo) {

        val taskEntity = todo.toTaskEntity()
        taskDao.update(taskEntity = taskEntity)

        val todoEntity = todo.toDataModel()
        todoDao.update(todoEntity)

        todo.schedule?.let { dateSchedule ->
            val dateScheduleEntity = dateSchedule.toDataModel(todoId = todo.id)
            dateScheduleDao.update(dateScheduleEntity)
        }

        rewardDao.deleteRewardsForTask(taskId = todo.id)
        todo.rewards?.let { rewards ->
            val rewardsEntity = rewards.toDataModel(taskId = todo.id)
            rewardDao.insert(rewardsEntity)
        }

    }

    override suspend fun delete(todo: Todo) {

        val taskEntity = todo.toTaskEntity()
        taskDao.delete(taskEntity = taskEntity)

    }

}
