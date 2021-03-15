package com.example.virtualfridge.data

import androidx.room.Embedded
import androidx.room.Relation

/**
 * This class captures the relationship between a [Consumable] and a user's [FridgeConsumable], which is
 * used by Room to fetch the related entities.
 */
data class ConsumableAndFridgeConsumables(
    @Embedded
    val consumable: Consumable,

    @Relation(parentColumn = "id", entityColumn = "consumable_id")
    val fridgeConsumables: List<FridgeConsumable> = emptyList()
)