package com.example.expensetracker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.expensetracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val expenseViewModel: ExpenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.buttonAddExpense.setOnClickListener {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, AddExpenseFragment())
                addToBackStack(null)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_view_summary -> {
                showSummary()
                true
            }
            R.id.action_view_all_expenses -> {
                showAllExpenses()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSummary() {
        val totalExpenses = expenseViewModel.expenses.value?.sumOf { it.value } ?: 0.0
        binding.textViewSummary.text = "Total Expenses: $$totalExpenses"
        binding.textViewSummary.visibility = View.VISIBLE
        supportFragmentManager.commit {
            replace(R.id.fragment_container, SummaryFragment())
            addToBackStack(null)
        }
    }

    private fun showAllExpenses() {
        binding.textViewSummary.visibility = View.GONE
        supportFragmentManager.commit {
            replace(R.id.fragment_container, ViewExpensesFragment())
            addToBackStack(null)
        }
    }
}
