package com.example.ezoassignmenttask.retroFit

import com.example.ezoassignmenttask.models.MainObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("kappa/image/task")
    fun getPosts(): Call<MainObject>

}


