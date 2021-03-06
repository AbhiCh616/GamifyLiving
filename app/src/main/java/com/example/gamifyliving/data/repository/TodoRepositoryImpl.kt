package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.*
import com.example.gamifyliving.data.data_source.local.mapper.*
import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.repository.TodoRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
    private val taskWithDetailsDao: TaskWithDetailsDao,
    private val todoDao: TodoDao,
    private val dateScheduleDao: DateScheduleDao,
    private val rewardDao: RewardDao,
) : TodoRepository {

    override fun observe() = taskWithDetailsDao.getAll().map {
        it.toTodoList()
    }

    override suspend fun getById(id: Int) =
        taskWithDetailsDao.getById(id = id)?.let { taskWithDetails ->
            if (taskWithDetails.todoWithSchedule != null) {
                taskWithDetails.toTodo()
            } else null
        }

    override suspend fun add(todo: Todo) {

        val taskEntity = todo.toTaskEntity()
        val taskId = taskDao.insert(taskEntity = taskEntity).toInt()

        val todoEntity = todo.toTodoEntity(taskId = taskId)
        todoDao.insert(todoEntity)

        todo.schedule?.let { dateSchedule ->
            val dateScheduleEntity = dateSchedule.toDateScheduleEntity(todoId = taskId)
            dateScheduleDao.insert(dateScheduleEntity)
        }

        todo.rewards?.let { rewards ->
            val rewardsEntity = rewards.toRewardEntityList(taskId = taskId)
            rewardDao.insert(rewardsEntity)
        }

    }

    override suspend fun update(todo: Todo) {

        val taskEntity = todo.toTaskEntity()
        taskDao.update(taskEntity = taskEntity)

        val todoEntity = todo.toTodoEntity()
        todoDao.update(todoEntity)

        dateScheduleDao.deleteByTodoId(todoId = todo.id)
        todo.schedule?.let { dateSchedule ->
            val dateScheduleEntity = dateSchedule.toDateScheduleEntity(todoId = todo.id)
            dateScheduleDao.update(dateScheduleEntity = dateScheduleEntity)
        }

        rewardDao.deleteByTaskId(taskId = todo.id)
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
