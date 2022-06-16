package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.table.StoreItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(storeItem: StoreItemEntity)

    @Update
    suspend fun update(storeItem: StoreItemEntity)

    @Delete
    suspend fun delete(storeItem: StoreItemEntity)

    @Query("SELECT * FROM store_item WHERE id = :id")
    suspend fun getStoreItemById(id: Int): StoreItemEntity?

    @Query("SELECT * FROM store_item")
    fun getAll(): Flow<List<StoreItemEntity>>

}
