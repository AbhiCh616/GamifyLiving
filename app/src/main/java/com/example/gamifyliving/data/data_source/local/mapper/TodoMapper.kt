package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.read_model.TaskWithDetailsEntity
import com.example.gamifyliving.data.data_source.local.model.table.TodoEntity
import com.example.gamifyliving.domain.model.entity.Todo

fun TaskWithDetailsEntity.toTodo() =
    Todo(
        id = task.id,
        name = task.name,
        coinsReward = todoWithSchedule!!.todo.coinsReward,
        schedule = todoWithSchedule.todoSchedule?.toDateSchedule(),
        status = task.status,
        rewards = rewards?.toRewardList()
    )

fun List<TaskWithDetailsEntity>.toTodoList() =
    this.filter { taskWithDetailsEntity ->
        taskWithDetailsEntity.todoWithSchedule != null
    }.map { taskWithDetailsEntity ->
        taskWithDetailsEntity.toTodo()
    }

fun Todo.toTodoEntity(taskId: Int? = null) =
    TodoEntity(
        coinsReward = coinsReward,
        taskId = taskId ?: id
    )
