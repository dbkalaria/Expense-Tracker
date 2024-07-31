package com.example.expensetracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.expensetracker.databinding.FragmentViewExpensesBinding

class ViewExpensesFragment : Fragment() {
    private var _binding: FragmentViewExpensesBinding? = null
    private val binding get() = _binding!!
    private val expenseViewModel: ExpenseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewExpensesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expenseViewModel.expenses.observe(viewLifecycleOwner) { expenses ->
            val expensesText = expenses.joinToString("\n") { expense ->
                "${expense.name}: ${expense.category}, $${expense.value}, ${expense.paymentType}"
            }
            binding.textViewExpenses.text = expensesText.ifEmpty { "No expenses yet" }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
