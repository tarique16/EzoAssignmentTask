package com.example.ezoassignmenttask

import android.content.Context
import androidx.core.content.ContextCompat

class Utility {

    companion object {

        fun setColor(context: Context, int: Int): Int {
            return ContextCompat.getColor(context, int)
        }
    }
}