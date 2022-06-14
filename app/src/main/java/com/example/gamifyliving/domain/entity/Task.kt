package com.example.gamifyliving.domain.entity

interface Task {
    val id: Int
    val name: String
    val status: Boolean
    val schedule: Schedule?
}
