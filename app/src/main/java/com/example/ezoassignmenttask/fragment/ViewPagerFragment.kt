package com.example.ezoassignmenttask.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.adapters.CoordinatorLayoutRecyclerAdapter
import com.example.ezoassignmenttask.adapters.CoordinatorListModel
import com.example.ezoassignmenttask.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {

    private var _binding:FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: CoordinatorLayoutRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        binding.apply {

        }
    }

    private fun initRecycler() {
        mAdapter = CoordinatorLayoutRecyclerAdapter()
        binding.rvCoordinator.adapter = mAdapter
        val list = ArrayList<CoordinatorListModel>()
        for (i in 0..100){
            list.add(
                CoordinatorListModel(i, "Test Name $i", i)
            )
        }
        mAdapter.submitList(list)
    }

}