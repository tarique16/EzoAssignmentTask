package com.example.ezoassignmenttask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ezoassignmenttask.models.Logs
import com.example.ezoassignmenttask.taskTwoClasses.ATM
import com.example.ezoassignmenttask.taskTwoClasses.Denomination
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class TaskTwoViewModel : ViewModel() {
    // Create a MutableLiveData to hold the deposit log data
    private val atm = ATM()
//    private val depositLog = mutableListOf<String>()
//    private val withdrawLog = mutableListOf<String>()
     val mainLogs = ArrayList<String>()
    private val _totalAmount = MutableLiveData<Int>()
    val denomination: MutableLiveData<List<Denomination>> = MutableLiveData()
    val totalAmount: LiveData<Int> get() = _totalAmount

    init {
        denomination.postValue(
            listOf(
                Denomination(2000, 0),
                Denomination(500, 0),
                Denomination(200, 0),
                Denomination(100, 0)
            )
        )
    }

    private var _uiEvent = MutableSharedFlow<Logger>()
    val uiEvents = _uiEvent.asSharedFlow()

    sealed class Logger {
        data object DepositLogger : Logger()
        data object WithdrawLogger : Logger()
        data object ErrorLogger : Logger()
    }

    private fun setDenomination(denominationList: List<Denomination>) {
        denomination.postValue(denominationList)
    }

    private fun setTotalAmount(amount: Int) {
        _totalAmount.postValue(amount)
    }

    suspend fun deposit(notes: Map<Int, Int>) {
        atm.deposit(notes)
        val totalAmountDeposited = atm.calculateTotalDepositedAmount()
        setDenomination(atm.getDenominations())
        val message = "Deposited[$totalAmountDeposited] \n${
            notes.entries.joinToString { "${it.key}: ${it.value}" }}"
//        depositLog.add(message)
        mainLogs.add(message)
        val res = Logger.DepositLogger
        _uiEvent.emit(res)
        setTotalAmount(totalAmountDeposited)
    }

    suspend fun withdraw(amount: Int) {
        val withdrawnDenominations = atm.withdraw(amount)
        if (withdrawnDenominations != null && amount > 0) {
            val message = "Withdrawn[$amount] \n${
                withdrawnDenominations.entries.joinToString { "${it.key}: ${it.value}" }
            }"
//            withdrawLog.add(message)
            mainLogs.add(message)
            val res = Logger.WithdrawLogger
            _uiEvent.emit(res)
        } else {
            val message = "Insufficient funds or denominations not available."
            mainLogs.add(message)
            _uiEvent.emit(Logger.ErrorLogger)
        }
        setDenomination(atm.getDenominations())
        setTotalAmount(atm.calculateTotalDepositedAmount())
    }
}

