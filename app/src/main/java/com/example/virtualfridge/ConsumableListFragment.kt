package com.example.virtualfridge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.virtualfridge.adapters.ConsumableAdapter
import com.example.virtualfridge.databinding.FragmentConsumableListBinding
import com.example.virtualfridge.viewmodels.ConsumableListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConsumableListFragment : Fragment(){

    private val viewModel: ConsumableListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentConsumableListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = ConsumableAdapter()
        binding.consumableList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: ConsumableAdapter) {
        viewModel.consumables.observe(viewLifecycleOwner) { consumables ->
            adapter.submitList(consumables)
        }
    }

    private fun updateData() {

    }

}