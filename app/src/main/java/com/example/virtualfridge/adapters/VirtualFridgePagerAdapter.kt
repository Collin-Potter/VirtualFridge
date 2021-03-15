package com.example.virtualfridge.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.virtualfridge.ConsumableListFragment
import com.example.virtualfridge.FridgeFragment
import java.lang.IndexOutOfBoundsException

const val MY_FRIDGE_PAGE_INDEX = 0
const val CONSUMABLE_LIST_PAGE_INDEX = 1

class VirtualFridgePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MY_FRIDGE_PAGE_INDEX to {FridgeFragment() },
        CONSUMABLE_LIST_PAGE_INDEX to { ConsumableListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}