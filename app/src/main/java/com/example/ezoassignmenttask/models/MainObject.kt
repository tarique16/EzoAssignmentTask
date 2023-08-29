package com.example.ezoassignmenttask.models

import com.example.ezoassignmenttask.models.MetaData

data class MainObject(
    val status: String,
    val items: List<MetaData>,
)