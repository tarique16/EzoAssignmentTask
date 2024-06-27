package com.example.ezoassignmenttask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.databinding.FragmentCoordinatorLayoutBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class CoordinatorLayoutFragment : Fragment() {

    private var _binding: FragmentCoordinatorLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoordinatorLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToobar()
        binding.apply {
            fab.setOnClickListener {
                Snackbar.make(fab, "Some ridiculously long text for snack",Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun initToobar() {
        binding.apply {
            vpFragmentContainer.adapter =
                AdditionalInvitersListPagerAdapter(childFragmentManager, lifecycle)
            vpFragmentContainer.isUserInputEnabled = false
            TabLayoutMediator(tlPageSelector, vpFragmentContainer) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.task_one_title)
                    1 -> tab.text = getString(R.string.task_two_title)
                }
            }.attach()
            tlPageSelector.post {
                tlPageSelector.getTabAt(0)?.select()
            }
        }
    }
}

class AdditionalInvitersListPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2 // Number of tabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ContactListFragment()
            1 -> ViewPagerFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}