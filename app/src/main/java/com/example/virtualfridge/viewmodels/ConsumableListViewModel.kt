package com.example.virtualfridge.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.virtualfridge.data.Consumable
import com.example.virtualfridge.data.ConsumableRepository

class ConsumableListViewModel @ViewModelInject internal constructor(
    consumableRepository: ConsumableRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val consumables: LiveData<List<Consumable>> = consumableRepository.getConsumables()
}