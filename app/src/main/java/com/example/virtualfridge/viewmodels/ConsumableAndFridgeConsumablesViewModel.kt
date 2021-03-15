package com.example.virtualfridge.viewmodels

import com.example.virtualfridge.data.ConsumableAndFridgeConsumables
import java.text.SimpleDateFormat
import java.util.*

class ConsumableAndFridgeConsumablesViewModel(consumables: ConsumableAndFridgeConsumables) {
    private val consumable = checkNotNull(consumables.consumable)
    private val fridgeConsumable = consumables.fridgeConsumables[0]

    val restockDateString: String = dateFormat.format(fridgeConsumable.lastRestockDate.time)
    val restockInterval
        get() = consumable.restockInterval
    val imageUrl
        get() = consumable.imageUrl
    val consumableName
        get() = consumable.name
    val consumableDateString: String = dateFormat.format(fridgeConsumable.restockDate.time)
    val consumableId
        get() = consumable.consumableId


    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    }
}