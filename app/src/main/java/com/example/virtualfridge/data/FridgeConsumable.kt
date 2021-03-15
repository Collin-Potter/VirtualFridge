package com.example.virtualfridge.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import java.util.Calendar

@Entity(
    tableName = "fridge_consumables",
    foreignKeys = [
    ForeignKey(entity = Consumable::class, parentColumns = ["id"], childColumns = ["consumable_id"])
    ],
    indices = [Index("consumable_id")]
)
data class FridgeConsumable (
    @ColumnInfo(name = "consumable_id") val consumableId: String,

    @ColumnInfo(name = "restock_date") val restockDate: Calendar = Calendar.getInstance(),

    @ColumnInfo(name = "last_restock_date")
    val lastRestockDate: Calendar = Calendar.getInstance()
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var fridgeConsumableId: Long = 0
}