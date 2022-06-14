package com.example.gamifyliving.domain.model.entity

interface Task {
    val id: Int
    val name: String
    val status: Boolean
    val schedule: Schedule?
    val rewards: List<Reward>?
}
