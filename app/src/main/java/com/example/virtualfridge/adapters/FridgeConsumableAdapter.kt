package com.example.virtualfridge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.virtualfridge.HomeViewPagerFragment
import com.example.virtualfridge.HomeViewPagerFragmentDirections
import com.example.virtualfridge.R
import com.example.virtualfridge.data.ConsumableAndFridgeConsumables
import com.example.virtualfridge.databinding.ListItemFridgeConsumableBinding
import com.example.virtualfridge.viewmodels.ConsumableAndFridgeConsumablesViewModel

class FridgeConsumableAdapter :
        ListAdapter<ConsumableAndFridgeConsumables, FridgeConsumableAdapter.ViewHolder>(
            FridgeConsumableDiffCallback()
        ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_fridge_consumable,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListItemFridgeConsumableBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.viewModel?.consumableId?.let { consumableId ->
                    navigateToConsumable(consumableId, view)
                }
            }
        }

        private fun navigateToConsumable(consumableId: String, view: View) {
            val direction = HomeViewPagerFragmentDirections
                .actionViewPagerFragmentToConsumableDetailFragment(consumableId)
            view.findNavController().navigate(direction)
        }

        fun bind(consumables: ConsumableAndFridgeConsumables) {
            with(binding) {
                viewModel = ConsumableAndFridgeConsumablesViewModel(consumables)
                executePendingBindings()
            }
        }
    }

}

private class FridgeConsumableDiffCallback :
    DiffUtil.ItemCallback<ConsumableAndFridgeConsumables>() {

    override fun areItemsTheSame(
        oldItem: ConsumableAndFridgeConsumables,
        newItem: ConsumableAndFridgeConsumables
    ): Boolean {
        return oldItem.consumable.consumableId == newItem.consumable.consumableId
    }

    override fun areContentsTheSame(
        oldItem: ConsumableAndFridgeConsumables,
        newItem: ConsumableAndFridgeConsumables
    ): Boolean {
        return oldItem.consumable == newItem.consumable
    }
}