package com.example.ezoassignmenttask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.ezoassignmenttask.databinding.FragmentMainTwoBinding


class MainTwoFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentMainTwoBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: AppViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            ivBack.setOnClickListener(this@MainTwoFragment)

            sharedViewModel = (requireActivity().application as MyApplication).sharedViewModel
            sharedViewModel.featureFlag.observe(viewLifecycleOwner) { flag ->
                ivThree.isVisible = flag
            }
            /*val remoteConfig = Firebase.remoteConfig
            remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
                override fun onUpdate(configUpdate: ConfigUpdate) {
                    remoteConfig.activate().addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            println("Remote updated parameters updated main two")

                            showButton = remoteConfig.getBoolean("show_task_three")
                            println("TARIQUE_TESTING_MAIN_TWO: $showButton")
                            binding.ivThree.isVisible = showButton
                        } else {
                            println("Remote updated parameters Failed main two")
                        }
                    }

                }

                override fun onError(error: FirebaseRemoteConfigException) {
//                TODO("Not yet implemented")
                }
            })*/
        }

    }

    override fun onClick(v: View?) {
        binding.apply {
            when (v?.id) {
                ivBack.id -> {
                    requireActivity().finish()
                }
            }
        }
    }

}