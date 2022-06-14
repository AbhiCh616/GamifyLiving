package com.example.gamifyliving.domain.model.entity

import com.example.gamifyliving.domain.model.value_object.Schedule

interface Task {
    val id: Int
    val name: String
    val status: Boolean
    val schedule: Schedule?
    val rewards: List<Reward>?
}
