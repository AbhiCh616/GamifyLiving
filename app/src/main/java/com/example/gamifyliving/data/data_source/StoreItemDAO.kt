package com.example.gamifyliving.data.data_source

import androidx.room.*
import com.example.gamifyliving.domain.model.StoreItem
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreItemDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(storeItem: StoreItem)

    @Update
    suspend fun update(storeItem: StoreItem)

    @Delete
    suspend fun delete(storeItem: StoreItem)

    @Query("SELECT * FROM storeitem")
    fun getAll(): Flow<List<StoreItem>>

}
