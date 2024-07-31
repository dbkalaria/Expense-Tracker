package com.example.expensetracker

data class Expense(
    val name: String,
    val category: String,
    val value: Double,
    val paymentType: String
)
