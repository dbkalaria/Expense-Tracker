package com.example.expensetracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.expensetracker.databinding.FragmentAddExpenseBinding

class AddExpenseFragment : Fragment() {
    private var _binding: FragmentAddExpenseBinding? = null
    private val binding get() = _binding!!
    private val expenseViewModel: ExpenseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSubmit.setOnClickListener {
            val expenseName = binding.editTextExpenseName.text.toString()
            val category = binding.spinnerCategory.selectedItem.toString()
            val expenseValue = binding.editTextExpenseValue.text.toString().toDoubleOrNull()
            val paymentType = when (binding.radioGroupPayment.checkedRadioButtonId) {
                R.id.radioButtonCash -> "Cash"
                R.id.radioButtonCard -> "Card"
                else -> ""
            }

            if (expenseName.isEmpty() || expenseValue == null || paymentType.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val expense = Expense(expenseName, category, expenseValue, paymentType)
                expenseViewModel.addExpense(expense)
                Toast.makeText(requireContext(), "Expense Added: $expenseName, $category, $expenseValue, $paymentType", Toast.LENGTH_SHORT).show()
                parentFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
