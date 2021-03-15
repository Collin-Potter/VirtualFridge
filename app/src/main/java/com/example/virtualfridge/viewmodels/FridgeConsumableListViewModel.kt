package com.example.virtualfridge.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.virtualfridge.data.ConsumableAndFridgeConsumables
import com.example.virtualfridge.data.FridgeConsumableRepository

class FridgeConsumableListViewModel @ViewModelInject internal constructor(
    fridgeConsumableRepository: FridgeConsumableRepository
) : ViewModel() {
    val consumablAndFridgeConsumables: LiveData<List<ConsumableAndFridgeConsumables>> =
        fridgeConsumableRepository.getRestockedFridges()
}