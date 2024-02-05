package com.example.ezoassignmenttask

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyApplication : Application() {

    val sharedViewModel: AppViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(this).create(AppViewModel::class.java)
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        CoroutineScope(Dispatchers.IO).launch {
            val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
            remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("config fetched")
                    val flag = remoteConfig.getBoolean("show_task_three")
                    sharedViewModel.setFeatureFlag(flag)
                }
            }
            remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
                override fun onUpdate(configUpdate: ConfigUpdate) {
                    remoteConfig.activate().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            println("config updated")
                            val flag = remoteConfig.getBoolean("show_task_three")
                            sharedViewModel.setFeatureFlag(flag)
                        }
                    }
                }

                override fun onError(error: FirebaseRemoteConfigException) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    /*private fun fetchRemoteConfig() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(5) // Set your desired cache expiration time
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.addOnConfigUpdateListener (object :ConfigUpdateListener{
            override fun onUpdate(configUpdate: ConfigUpdate) {
                remoteConfig.activate()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Values are updated, you can use them here
                            println("Remote updated parameters updated")
                        } else {
                            println("Remote updated parameters Failed")
                        }
                    }
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                TODO("Not yet implemented")
            }
        }

        )
    }*/
}
