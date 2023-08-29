package com.example.ezoassignmenttask.taskTwoClasses

import kotlin.math.min


class ATM {

    private val denominations = mutableListOf(
        Denomination(2000, 0),
        Denomination(500, 0),
        Denomination(200, 0),
        Denomination(100, 0)
    )

    fun deposit(notes: Map<Int, Int>) {
        for ((noteValue, noteCount) in notes) {
            val denomination = denominations.find { it.value == noteValue }
            denomination?.count = denomination?.count?.plus(noteCount) ?: 0
        }
    }

    fun withdraw(amount: Int): Map<Int, Int>? {
        val withdrawnDenominations = mutableMapOf<Int, Int>()
        var remainingAmount = amount

        // Clone the denominations list to avoid modifying the original list
        val availableDenominations = denominations.toList()

        for (denomination in availableDenominations) {
            val noteValue = denomination.value
            val noteCount = denomination.count

            if (noteValue <= remainingAmount && noteCount > 0) {
                val notesToWithdraw = min(remainingAmount / noteValue, noteCount)
                withdrawnDenominations[noteValue] = notesToWithdraw
                remainingAmount -= notesToWithdraw * noteValue
            }
        }

        return if (remainingAmount == 0) {
            for ((noteValue, noteCount) in withdrawnDenominations) {
                val denomination = denominations.find { it.value == noteValue }
                denomination?.count = denomination?.count?.minus(noteCount) ?: 0
            }
            withdrawnDenominations
        } else {
            null
        }
    }

    fun getDenominations(): List<Denomination> {
        return denominations.toList()
    }

    fun calculateTotalDepositedAmount(): Int {
        var totalAmount = 0
        for (denomination in denominations) {
            totalAmount += denomination.value * denomination.count
        }
        return totalAmount
    }
}
