package com.example.gamifyliving.domain.model

interface Task {
    val id: Int
    val name: String
    val status: Boolean
    val schedule: Schedule?
}
