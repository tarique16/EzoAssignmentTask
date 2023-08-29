package com.example.ezoassignmenttask.fragment

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.adapters.TaskTwoRecyclerAdapter
import com.example.ezoassignmenttask.databinding.FragmentTaskTwoBinding
import com.example.ezoassignmenttask.models.Logs
import com.example.ezoassignmenttask.viewModel.TaskTwoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TaskTwoFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentTaskTwoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TaskTwoViewModel
    private lateinit var recyclerAdapter: TaskTwoRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TaskTwoViewModel::class.java]
        setListeners()
        setZeroValues()
        observers()
    }

    private fun observers() {
        recyclerAdapter = TaskTwoRecyclerAdapter()
        binding.rvLogger.adapter = recyclerAdapter
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.uiEvents.collectLatest { event ->
                val newList = viewModel.mainLogs
                when (event) {
                    is TaskTwoViewModel.Logger.ErrorLogger -> {
                        recyclerAdapter.notifyItemInserted(0)
                        binding.rvLogger.smoothScrollToPosition(0)
                        openKeyboard(binding.etWithdrawAmount)
                    }

                    is TaskTwoViewModel.Logger.DepositLogger -> {
                        recyclerAdapter.notifyItemInserted(0)
                        binding.rvLogger.smoothScrollToPosition(0)
                    }

                    is TaskTwoViewModel.Logger.WithdrawLogger -> {
                        recyclerAdapter.notifyItemInserted(0)
                        binding.rvLogger.smoothScrollToPosition(0)
                    }
                }
                val loggers = newList.map { Logs(it) }
                recyclerAdapter.submitList(loggers.reversed())
            }
        }

        viewModel.totalAmount.observe(viewLifecycleOwner) { total ->
            binding.tvTotal.text = total.toString()
        }

        viewModel.denomination.observe(viewLifecycleOwner) { denominationList ->
            for (element in denominationList) {
                when (element.value) {
                    2000 -> {
                        binding.tvTwoThousandQty.text = "${element.count}   |"
                    }

                    500 -> {
                        binding.tvFiveHundredQty.text = "${element.count}   |"
                    }

                    200 -> {
                        binding.tvTwoHundredQty.text = "${element.count}   |"
                    }

                    100 -> {
                        binding.tvOneHundredQty.text = "${element.count}   |"
                    }
                }
            }
        }
    }

    private fun setListeners() {
        binding.ivBack.setOnClickListener(this)
        binding.tvDeposit.setOnClickListener(this)
        binding.tvWithdraw.setOnClickListener(this)
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private fun openKeyboard(editText: EditText) {
        editText.requestFocus()
        val inputMethodManager =
            requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun setZeroValues() {
        binding.apply {
            etTwoThousand.setText("0")
            etFiveHundred.setText("0")
            etTwoHundred.setText("0")
            etOneHundred.setText("0")
        }
    }

    override fun onClick(p0: View?) {
        binding.apply {
            when (p0?.id) {
                ivBack.id -> {
                    findNavController().navigate(R.id.action_taskTwoFragment_to_mainFragment)
                }

                tvDeposit.id -> {
                    val notes = mapOf(
                        2000 to etTwoThousand.text.toString().toInt(),
                        500 to etFiveHundred.text.toString().toInt(),
                        200 to etTwoHundred.text.toString().toInt(),
                        100 to etOneHundred.text.toString().toInt()
                    )
                    CoroutineScope(Dispatchers.Main).launch { viewModel.deposit(notes) }
                    hideKeyboard()
                }

                tvWithdraw.id -> {
                    val amount =
                        if (etWithdrawAmount.text.isEmpty()) 0 else etWithdrawAmount.text.toString()
                            .toInt()
                    CoroutineScope(Dispatchers.Main).launch { viewModel.withdraw(amount) }
                    etWithdrawAmount.setText("")
                    hideKeyboard()
                }
            }
        }
    }
}




