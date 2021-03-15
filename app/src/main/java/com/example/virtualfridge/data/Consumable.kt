package com.example.virtualfridge.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.Calendar.DAY_OF_YEAR

@Entity(tableName = "consumables")
data class Consumable(
    @PrimaryKey @ColumnInfo(name = "id") val consumableId: String,
    val name: String,
    val description: String,
    val restockInterval: Int = 7, // How often the consumable should be restocked
    val imageUrl: String = ""
) {

    fun shouldBeRestocked(since: Calendar, lastRestockDate: Calendar) =
        since > lastRestockDate.apply { add(DAY_OF_YEAR, restockInterval) }

    override fun toString() = name

}