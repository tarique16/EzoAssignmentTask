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

    private val atm = ATM()
    private val _totalAmount = MutableLiveData<Int>()
    val denomination: MutableLiveData<List<Denomination>> = MutableLiveData()
    val totalAmount: LiveData<Int> get() = _totalAmount
    private val _logData = MutableLiveData<List<Logs>>()
    val logData: LiveData<List<Logs>> get() = _logData

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

    private fun addLog(log: String) {
        val logs = _logData.value?.toMutableList() ?: mutableListOf()
        logs.add(Logs(log))
        _logData.value = logs
    }

    suspend fun deposit(notes: Map<Int, Int>) {
        atm.deposit(notes)
        val totalAmountDeposited = atm.calculateTotalDepositedAmount()
        setDenomination(atm.getDenominations())
        val message = "Deposited[$totalAmountDeposited] \n${
            notes.entries.joinToString { "${it.key}: ${it.value}" }
        }"
        addLog(message)
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
            addLog(message)
            val res = Logger.WithdrawLogger
            _uiEvent.emit(res)
        } else {
            val message = "Insufficient funds or denominations not available."
            addLog(message)
            _uiEvent.emit(Logger.ErrorLogger)
        }
        setDenomination(atm.getDenominations())
        setTotalAmount(atm.calculateTotalDepositedAmount())
    }
}

