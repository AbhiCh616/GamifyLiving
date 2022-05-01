package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.TaskEntity
import com.example.gamifyliving.data.data_source.local.model.TaskWithDetailsEntity
import com.example.gamifyliving.data.data_source.local.model.TodoEntity
import com.example.gamifyliving.data.data_source.local.model.TodoWithScheduleEntity
import com.example.gamifyliving.domain.model.DateSchedule
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.model.TimeSpan
import com.example.gamifyliving.domain.model.Todo

fun TaskWithDetailsEntity.toDomainModel(): Task =
    this.todoWithSchedule?.let {

    }
Todo(
name = this.task.name,
status = this.task.status,
coinsReward = this.todoWithSchedule!!.todo.coinsReward,
schedule = this.todoWithSchedule.todoSchedule?.scheduledDate.let {
    scheduledDate ->
    DateSchedule(
        date = scheduledDate,
        timeSpan = startTime?.let {
            TimeSpan(
                startTime = this.startTime,
                endTime = this.endTime!!
            )
        }
    )
},
id = this.id
)




fun List<Task>.toDataModel() =
    this.map { task -> task.toDataModel() }
