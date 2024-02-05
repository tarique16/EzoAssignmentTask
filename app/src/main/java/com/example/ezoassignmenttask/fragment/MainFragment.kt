package com.example.ezoassignmenttask.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ezoassignmenttask.AppViewModel
import com.example.ezoassignmenttask.MainTwoActivity
import com.example.ezoassignmenttask.MyApplication
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.databinding.FragmentMainBinding

class MainFragment : Fragment(), View.OnClickListener {
    // Inside an Activity or Fragment

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        /*
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener{
            override fun onUpdate(configUpdate: ConfigUpdate) {
                remoteConfig.activate().addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        println("Remote updated parameters updated")

                        val showButton: Boolean = remoteConfig.getBoolean("show_task_three")
                        println("TARIQUE_TESTING: $showButton")
                        binding.tvTaskThree.isVisible = showButton
                    } else {
                        println("Remote updated parameters Failed")
                    }
                }

            }

            override fun onError(error: FirebaseRemoteConfigException) {
//                TODO("Not yet implemented")
            }
        })*/
        sharedViewModel = (requireActivity().application as MyApplication).sharedViewModel
        sharedViewModel.featureFlag.observe(viewLifecycleOwner) { flag ->
            binding.tvTaskThree.isVisible = flag
        }

    }

    private fun setListeners() {
        binding.apply {
            tvTaskOne.setOnClickListener(this@MainFragment)
            tvTaskTwo.setOnClickListener(this@MainFragment)
            tvTaskThree.setOnClickListener(this@MainFragment)
            ivFilter.setOnClickListener(this@MainFragment)
        }
    }

    override fun onClick(p0: View?) {
        binding.apply {
            when (p0?.id) {
                tvTaskOne.id -> {
                    findNavController().navigate(R.id.action_mainFragment_to_taskOneFragment)
                }

                tvTaskTwo.id -> {
                    findNavController().navigate(R.id.action_mainFragment_to_taskTwoFragment)
                }

                tvTaskThree.id -> {
                    findNavController().navigate(R.id.action_mainFragment_to_taskThreeFragment)
                }

                ivFilter.id -> {
                    val intent = Intent(requireActivity(), MainTwoActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}