package com.example.ezoassignmenttask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    private val _featureFlag : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val featureFlag: LiveData<Boolean> get() = _featureFlag

    fun setFeatureFlag(flag: Boolean) {
        _featureFlag.postValue(flag)
    }
}