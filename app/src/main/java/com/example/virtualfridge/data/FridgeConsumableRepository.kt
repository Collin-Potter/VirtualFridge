package com.example.virtualfridge.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FridgeConsumableRepository @Inject constructor(
    private val fridgeConsumableDao: FridgeConsumableDao
) {

    suspend fun createFridgeConsumable(consumableId: String) {
        val fridgeConsumable = FridgeConsumable(consumableId)
        fridgeConsumableDao.insertFridgeConsumable(fridgeConsumable)
    }

    suspend fun removeFridgeConsumable(fridgeConsumable: FridgeConsumable) {
        fridgeConsumableDao.deleteFridgeConsumable(fridgeConsumable)
    }

    fun isRestocked(consumableId: String) =
        fridgeConsumableDao.isRestocked(consumableId)

    fun getRestockedFridges() = fridgeConsumableDao.getRestockedFridges()
}