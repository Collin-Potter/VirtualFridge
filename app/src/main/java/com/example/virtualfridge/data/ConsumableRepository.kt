package com.example.virtualfridge.data

import javax.inject.Inject

class ConsumableRepository @Inject constructor(private val consumableDao: ConsumableDao) {

    fun getConsumables() = consumableDao.getConsumables()

    fun getConsumable(consumableId: String) = consumableDao.getConsumable(consumableId)

}