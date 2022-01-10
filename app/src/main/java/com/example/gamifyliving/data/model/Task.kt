package com.example.gamifyliving.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (indices = [Index(value = ["name"], unique = true)])

data class Task (
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "status")
    val status: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
)