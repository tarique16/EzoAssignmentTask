package com.example.ezoassignmenttask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.adapters.TaskOneRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.ezoassignmenttask.databinding.FragmentTaskOneBinding
import com.example.ezoassignmenttask.retroFit.ApiServices
import com.example.ezoassignmenttask.models.MainObject
import com.example.ezoassignmenttask.retroFit.RetrofitClient


class TaskOneFragment : Fragment(),View.OnClickListener {

    private var _binding:FragmentTaskOneBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerAdapter: TaskOneRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTaskOneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApi()
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            ivBack.setOnClickListener(this@TaskOneFragment)
        }
    }

    private fun callApi(){
        val apiService = RetrofitClient.retrofit.create(ApiServices::class.java)
        val call = apiService.getPosts()
        call.enqueue(object : Callback<MainObject> {
            override fun onResponse(call: Call<MainObject>, response: Response<MainObject>) {
                if (response.isSuccessful) {
                    val metaData = response.body()
                    if (metaData != null){
                        recyclerAdapter = TaskOneRecyclerAdapter(metaData.items)
                        binding.rvContents.adapter = recyclerAdapter
                    }
                    Toast.makeText(requireActivity(),"Successful",Toast.LENGTH_SHORT).show()
                } else {
                    // Handle error
                    Toast.makeText(requireActivity(),"Api calling failed: $response",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MainObject>, t: Throwable) {
                // Handle failure
                Toast.makeText(requireActivity(),"Something went wrong",Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(p0: View?) {
        binding.apply {
            when(p0?.id){
                ivBack.id -> {
                    findNavController().navigate(R.id.action_taskOneFragment_to_mainFragment)
                }
            }
        }
    }
}