package com.example.virtualfridge

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ShareCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.virtualfridge.data.Consumable
import com.example.virtualfridge.databinding.FragmentConsumableDetailBinding
import com.example.virtualfridge.viewmodels.ConsumableDetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_gallery.*
import javax.inject.Inject

@AndroidEntryPoint
class ConsumableDetailFragment : Fragment() {

    private val args: ConsumableDetailFragmentArgs by navArgs()

    @Inject
    lateinit var consumableDetailViewModelFactory: ConsumableDetailViewModel.AssistedFactory

    private val consumableDetailViewModel: ConsumableDetailViewModel by viewModels {
        ConsumableDetailViewModel.provideFactory(
            consumableDetailViewModelFactory,
            args.consumableId
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentConsumableDetailBinding>(
            inflater,
            R.layout.fragment_consumable_detail,
            container,
            false
        ).apply {
            viewModel = consumableDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = Callback { consumable ->
                consumable?.let {
                    hideAppBarFab(fab)
                    consumableDetailViewModel.addConsumableToFridge()
                    Snackbar.make(root, "Added consumable to fridge", Snackbar.LENGTH_LONG)
                        .show()
                }
            }

            galleryNav.setOnClickListener { navigateToGallery() }

            var isToolbarShown = false

            consumableDetailScrollview.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                    val shouldShowToolbar = scrollY > toolbar.height

                    if (isToolbarShown != shouldShowToolbar) {
                        isToolbarShown = shouldShowToolbar

                        appbar.isActivated = shouldShowToolbar

                        toolbarLayout.isTitleEnabled = shouldShowToolbar
                    }

                }
            )

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }

            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_share -> {
                        createShareIntent()
                        true
                    }
                    else -> false
                }
            }
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun navigateToGallery() {
        consumableDetailViewModel.consumable.value?.let { consumable ->
            val direction =
                ConsumableDetailFragmentDirections.actionConsumableDetailFragmentToGalleryFragment(consumable.name)
            findNavController().navigate(direction)
        }
    }

    @Suppress("DEPRECATION")
    private fun createShareIntent() {
        val shareText = consumableDetailViewModel.consumable.value.let { consumable ->
            if (consumable == null) {
                ""
            } else {
                //TODO: Add share text dynamic to consumable chosen
                ""
            }
        }
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setText(shareText)
            .setType("text/plain")
            .createChooserIntent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(shareIntent)
    }

    private fun hideAppBarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }

    fun interface Callback {
        fun add(consumable: Consumable?)
    }
}