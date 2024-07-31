package com.example.expensetracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.expensetracker.databinding.FragmentUpdateExpenseBinding

class UpdateExpenseFragment : Fragment() {
    private var _binding: FragmentUpdateExpenseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUpdate.setOnClickListener {
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
                // Update expense logic
                Toast.makeText(requireContext(), "Expense Updated: $expenseName, $category, $expenseValue, $paymentType", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
