package com.example.ezoassignmenttask.fragment

import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.Utility.Companion.setColor
import com.example.ezoassignmenttask.adapters.TaskTwoRecyclerAdapter
import com.example.ezoassignmenttask.databinding.FragmentTaskTwoBinding
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
    private val drawable: Drawable? by lazy {
        ResourcesCompat.getDrawable(resources, R.drawable.rectangle_border, null)
    }
    private val backgroundDrawable: GradientDrawable? by lazy {
        drawable?.constantState?.newDrawable()?.mutate() as? GradientDrawable
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TaskTwoViewModel::class.java]
        setListeners()
        observers()
        recyclerAdapter = TaskTwoRecyclerAdapter()
        binding.rvLogger.adapter = recyclerAdapter
    }

    private fun observers() {

        viewModel.logData.observe(viewLifecycleOwner) { logList ->
            recyclerAdapter.submitList(logList.reversed())
        }

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.uiEvents.collectLatest { event ->
                when (event) {
                    is TaskTwoViewModel.Logger.ErrorLogger -> {
                        binding.apply {
                            rvLogger.smoothScrollToPosition(0)
                            etWithdrawAmount.setShakeAnimationAndOpenKeyboard(
                                backgroundDrawable,
                                R.color.log_red,
                                R.color.log_red_text
                            )
                        }
                    }

                    is TaskTwoViewModel.Logger.DepositLogger -> {
                        binding.rvLogger.smoothScrollToPosition(0)
                    }

                    is TaskTwoViewModel.Logger.WithdrawLogger -> {
                        binding.apply {
                            rvLogger.smoothScrollToPosition(0)
                            etWithdrawAmount.background = drawable
                        }
                    }
                }
            }
        }

        viewModel.totalAmount.observe(viewLifecycleOwner) { total ->
            binding.tvTotal.text = total.toString()
        }

        viewModel.denomination.observe(viewLifecycleOwner) { denominationList ->
            val denominationTextViews = mapOf(
                2000 to binding.tvTwoThousandQty,
                500 to binding.tvFiveHundredQty,
                200 to binding.tvTwoHundredQty,
                100 to binding.tvOneHundredQty
            )

            for (element in denominationList) {
                val textView = denominationTextViews[element.value]
                textView?.text = "x ${element.count}"
            }
        }
    }

    // Extension function for setting shake animation and opening the keyboard
    private fun EditText.setShakeAnimationAndOpenKeyboard(
        backgroundDrawable: GradientDrawable?,
        textColorResId: Int,
        strokeColorResId: Int
    ) {
        val textColor = setColor(context, textColorResId)
        val strokeColor = setColor(context, strokeColorResId)

        backgroundDrawable?.let {
            it.setStroke(2, strokeColor)
            it.setColor(textColor)
        }
        this.background = backgroundDrawable
        val shakeAnimation = AnimationUtils.loadAnimation(context, R.anim.shake_anim)
        this.startAnimation(shakeAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
            openKeyboard(this)
        }, shakeAnimation.duration)
    }

    private fun setListeners() {
        binding.ivBack.setOnClickListener(this)
        binding.tvDeposit.setOnClickListener(this)
        binding.tvWithdraw.setOnClickListener(this)
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private fun openKeyboard(editText: EditText) {
        editText.apply {
            requestFocus()
            val inputMethodManager =
                context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun setAllDepositETValues(value: String) {
        binding.apply {
            etTwoThousand.setText(value)
            etFiveHundred.setText(value)
            etTwoHundred.setText(value)
            etOneHundred.setText(value)
        }
    }

    override fun onClick(p0: View?) {
        binding.apply {
            when (p0?.id) {
                ivBack.id -> {
                    findNavController().navigate(R.id.action_taskTwoFragment_to_mainFragment)
                }

                tvDeposit.id -> {
                    val denominations = listOf(2000, 500, 200, 100)
                    val notes = mutableMapOf<Int, Int>()

                    for (denomination in denominations) {
                        val editText = when (denomination) {
                            2000 -> etTwoThousand
                            500 -> etFiveHundred
                            200 -> etTwoHundred
                            100 -> etOneHundred
                            else -> null
                        }

                        val count = editText?.text.toString().toIntOrNull() ?: 0
                        notes[denomination] = count
                    }

                    viewModel.viewModelScope.launch { viewModel.deposit(notes) }
                    setAllDepositETValues("")
                    hideKeyboard()
                }

                tvWithdraw.id -> {
                    val amount =
                        if (etWithdrawAmount.text.isEmpty()) 0 else etWithdrawAmount.text.toString()
                            .toInt()
                    viewModel.viewModelScope.launch { viewModel.withdraw(amount) }
                    etWithdrawAmount.setText("")
                    hideKeyboard()
                }
            }
        }
    }
}
