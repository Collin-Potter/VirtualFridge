package com.example.virtualfridge.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ConsumableDao {
    @Query("SELECT * FROM consumables ORDER BY name")
    fun getConsumables(): LiveData<List<Consumable>>

    @Query("SELECT * FROM consumables WHERE id = :consumableId")
    fun getConsumable(consumableId: String): LiveData<Consumable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(consumables: List<Consumable>)
}