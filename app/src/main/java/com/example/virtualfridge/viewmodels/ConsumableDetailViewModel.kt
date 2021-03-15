package com.example.virtualfridge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.virtualfridge.BuildConfig
import com.example.virtualfridge.data.ConsumableRepository
import com.example.virtualfridge.data.FridgeConsumableRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

class ConsumableDetailViewModel @AssistedInject constructor(
    consumableRepository: ConsumableRepository,
    private val fridgeConsumableRepository: FridgeConsumableRepository,
    @Assisted private val consumableId: String
) : ViewModel() {

    val isRestocked = fridgeConsumableRepository.isRestocked(consumableId)
    val consumable = consumableRepository.getConsumable(consumableId)

    fun addConsumableToFridge() {
        viewModelScope.launch {
            fridgeConsumableRepository.createFridgeConsumable(consumableId)
        }
    }

    fun hasValidUnsplashKey() = (BuildConfig.UNSPLASH_ACCESS_KEY != "null")

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(consumableId: String): ConsumableDetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            consumableId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(consumableId) as T
            }
        }
    }
}