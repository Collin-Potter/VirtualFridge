package com.example.virtualfridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.virtualfridge.adapters.CONSUMABLE_LIST_PAGE_INDEX
import com.example.virtualfridge.adapters.MY_FRIDGE_PAGE_INDEX
import com.example.virtualfridge.adapters.VirtualFridgePagerAdapter
import com.example.virtualfridge.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IndexOutOfBoundsException

@AndroidEntryPoint
class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = VirtualFridgePagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_FRIDGE_PAGE_INDEX -> R.drawable.fridge_tab_selector
            CONSUMABLE_LIST_PAGE_INDEX -> R.drawable.consumable_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_FRIDGE_PAGE_INDEX -> getString(R.string.my_fridge_title)
            CONSUMABLE_LIST_PAGE_INDEX -> getString(R.string.consumable_list_title)
            else -> null
        }
    }
}