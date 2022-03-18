package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.domain.model.StoreItem
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(storeItem: StoreItem)

    @Update
    suspend fun update(storeItem: StoreItem)

    @Delete
    suspend fun delete(storeItem: StoreItem)

    @Query("SELECT * FROM storeItem WHERE uid = :id")
    suspend fun getStoreItemById(id: Int): StoreItem?

    @Query("SELECT * FROM storeItem")
    fun getAll(): Flow<List<StoreItem>>

}
