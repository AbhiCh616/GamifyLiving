package com.example.gamifyliving.domain.use_case

data class TaskUseCases(
    val addTask: AddTask,
    val updateTask: UpdateTask,
    val deleteTask: DeleteTask,
    val getTasks: GetTasks
)
