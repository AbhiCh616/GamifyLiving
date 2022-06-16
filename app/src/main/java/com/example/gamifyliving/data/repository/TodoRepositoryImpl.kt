package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.DateScheduleDao
import com.example.gamifyliving.data.data_source.local.dao.RewardDao
import com.example.gamifyliving.data.data_source.local.dao.TaskDao
import com.example.gamifyliving.data.data_source.local.dao.TodoDao
import com.example.gamifyliving.data.data_source.local.mapper.toRewardEntityList
import com.example.gamifyliving.data.data_source.local.mapper.toDateScheduleEntity
import com.example.gamifyliving.data.data_source.local.mapper.toTodoEntity
import com.example.gamifyliving.data.data_source.local.mapper.toTaskEntity
import com.example.gamifyliving.data.data_source.local.mapper.toTodoList
import com.example.gamifyliving.data.data_source.local.mapper.toTodo
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
        it.toTodoList()
    }

    override suspend fun getById(id: Int): Todo? =
        taskDao.getTaskWithDetails(id = id)?.toTodo()

    override suspend fun add(todo: Todo) {

        val taskEntity = todo.toTaskEntity()
        taskDao.insert(taskEntity = taskEntity)

        val todoEntity = todo.toTodoEntity()
        todoDao.insert(todoEntity)

        todo.schedule?.let { dateSchedule ->
            val dateScheduleEntity = dateSchedule.toDateScheduleEntity(todoId = todo.id)
            dateScheduleDao.insert(dateScheduleEntity)
        }

        todo.rewards?.let { rewards ->
            val rewardsEntity = rewards.toRewardEntityList(taskId = todo.id)
            rewardDao.insert(rewardsEntity)
        }

    }

    override suspend fun update(todo: Todo) {

        val taskEntity = todo.toTaskEntity()
        taskDao.update(taskEntity = taskEntity)

        val todoEntity = todo.toTodoEntity()
        todoDao.update(todoEntity)

        todo.schedule?.let { dateSchedule ->
            val dateScheduleEntity = dateSchedule.toDateScheduleEntity(todoId = todo.id)
            dateScheduleDao.update(dateScheduleEntity)
        }

        rewardDao.deleteRewardsForTask(taskId = todo.id)
        todo.rewards?.let { rewards ->
            val rewardsEntity = rewards.toRewardEntityList(taskId = todo.id)
            rewardDao.insert(rewardsEntity)
        }

    }

    override suspend fun delete(todo: Todo) {

        val taskEntity = todo.toTaskEntity()
        taskDao.delete(taskEntity = taskEntity)

    }

}
