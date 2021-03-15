package com.example.virtualfridge.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FridgeConsumableDao {
    @Query("SELECT * FROM fridge_consumables")
    fun getFridgeConsumables(): LiveData<List<FridgeConsumable>>

    @Query("SELECT EXISTS(SELECT 1 FROM fridge_consumables WHERE consumable_id = :consumableId LIMIT 1)")
    fun isRestocked(consumableId: String): LiveData<Boolean>

    @Transaction
    @Query("SELECT * FROM consumables WHERE id IN (SELECT DISTINCT(consumable_id) FROM fridge_consumables)")
    fun getRestockedFridges(): LiveData<List<ConsumableAndFridgeConsumables>>

    @Insert
    suspend fun insertFridgeConsumable(fridgeConsumable: FridgeConsumable): Long

    @Delete
    suspend fun deleteFridgeConsumable(fridgeConsumable: FridgeConsumable)
}