package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.table.DateScheduleEntity

@Dao
interface DateScheduleDao {

    @Query("DELETE FROM date_schedule WHERE todo_id = :todoId")
    suspend fun deleteByTodoId(todoId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dateScheduleEntity: DateScheduleEntity)

    @Update
    suspend fun update(dateScheduleEntity: DateScheduleEntity)

}
