package com.example.virtualfridge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.virtualfridge.databinding.ActivityFridgeBinding
import androidx.databinding.DataBindingUtil.setContentView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FridgeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityFridgeBinding>(this, R.layout.activity_fridge)
    }
}