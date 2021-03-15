package com.example.virtualfridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.virtualfridge.adapters.CONSUMABLE_LIST_PAGE_INDEX
import com.example.virtualfridge.adapters.FridgeConsumableAdapter
import com.example.virtualfridge.databinding.FragmentFridgeBinding
import com.example.virtualfridge.viewmodels.FridgeConsumableListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FridgeFragment : Fragment() {

    private lateinit var binding: FragmentFridgeBinding

    private val viewModel: FridgeConsumableListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFridgeBinding.inflate(inflater, container, false)
        val adapter = FridgeConsumableAdapter()
        binding.fridgeList.adapter = adapter

        binding.addConsumable.setOnClickListener {
            navigateToConsumableListPage()
        }

        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: FridgeConsumableAdapter, binding: FragmentFridgeBinding) {
        viewModel.consumablAndFridgeConsumables.observe(viewLifecycleOwner) { result ->
            binding.hasConsumables = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

    private fun navigateToConsumableListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            CONSUMABLE_LIST_PAGE_INDEX
    }
}