package com.example.ezoassignmenttask.models

import android.graphics.drawable.Drawable

data class MetaData(
    val itemName: String,
    val itemPrice: Int,
    val itemBarcode: String,
    val url: String
)

data class FourMetaData(
    val id: Int,
    val url: String? = "",
    val itemName: String? = "",
    val itemPrice: Int? = 0,
    val itemBarcode: Int? = 0,
    val drawable: Drawable? = null,
    val viewType: Int
)