package com.example.gamifyliving.data.data_source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "stat",
    indices = [Index(value = ["name"], unique = true)]
)

data class StatEntity(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "value")
    val value: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int
)
