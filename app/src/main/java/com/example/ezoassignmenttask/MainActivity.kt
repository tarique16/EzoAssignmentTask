package com.example.ezoassignmenttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ezoassignmenttask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    private lateinit var sharedViewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedViewModel = (application as MyApplication).sharedViewModel
        sharedViewModel.featureFlag.observe(this) { flag ->
            println(flag)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}