package com.example.virtualfridge.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.virtualfridge.data.AppDatabase
import com.example.virtualfridge.data.Consumable
import com.example.virtualfridge.utilities.CONSUMABLE_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(CONSUMABLE_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val consumableType = object : TypeToken<List<Consumable>>() {}.type
                    val consumableList: List<Consumable> = Gson().fromJson(jsonReader, consumableType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.consumableDao().insertAll(consumableList)

                    Log.d(TAG, "Success seeding database")
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}