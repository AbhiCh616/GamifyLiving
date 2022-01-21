package com.example.gamifyliving.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Stat", indices = [Index(value = ["name"], unique = true)])

data class Stat(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "value")
    val value: Int,

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
)
