package com.example.virtualfridge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.virtualfridge.HomeViewPagerFragmentDirections
import com.example.virtualfridge.data.Consumable
import com.example.virtualfridge.databinding.ListItemConsumableBinding

class ConsumableAdapter : ListAdapter<Consumable, RecyclerView.ViewHolder>
    (ConsumableDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ConsumableViewHolder(
            ListItemConsumableBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val consumable = getItem(position)
        (holder as ConsumableViewHolder).bind(consumable)
    }

    class ConsumableViewHolder(
        private val binding: ListItemConsumableBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.consumable?.let { consumable ->
                    navigateToConsumable(consumable, it)
                }
            }
        }

        private fun navigateToConsumable(
            consumable: Consumable,
            view: View
        ) {
            val direction =
                HomeViewPagerFragmentDirections.actionViewPagerFragmentToConsumableDetailFragment(
                    consumable.consumableId
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item: Consumable) {
            binding.apply {
                consumable = item
                executePendingBindings()
            }
        }
    }

}

private class ConsumableDiffCallback : DiffUtil.ItemCallback<Consumable>() {

    override fun areItemsTheSame(oldItem: Consumable, newItem: Consumable): Boolean {
        return oldItem.consumableId == newItem.consumableId
    }

    override fun areContentsTheSame(oldItem: Consumable, newItem: Consumable): Boolean {
        return oldItem == newItem
    }
}