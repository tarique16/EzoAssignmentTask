package com.example.ezoassignmenttask.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.databinding.FragmentMainBinding

class MainFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners(){
        binding.apply {
            tvTaskOne.setOnClickListener(this@MainFragment)
            tvTaskTwo.setOnClickListener(this@MainFragment)
        }
    }
    override fun onClick(p0: View?) {
        binding.apply {
            when (p0?.id) {
                tvTaskOne.id -> {
                    findNavController().navigate(R.id.action_mainFragment_to_taskOneFragment)
//                    Toast.makeText(requireActivity(), "TaskOne", Toast.LENGTH_SHORT).show()
                }

                tvTaskTwo.id -> {
                    findNavController().navigate(R.id.action_mainFragment_to_taskTwoFragment)
                    Toast.makeText(requireActivity(), "TaskTwo", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}