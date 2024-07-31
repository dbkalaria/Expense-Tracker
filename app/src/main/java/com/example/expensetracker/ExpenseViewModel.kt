package com.example.expensetracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExpenseViewModel : ViewModel() {
    private val _expenses = MutableLiveData<MutableList<Expense>>(mutableListOf())
    val expenses: LiveData<MutableList<Expense>> get() = _expenses

    fun addExpense(expense: Expense) {
        _expenses.value?.add(expense)
        _expenses.value = _expenses.value // Trigger LiveData update
    }
}
