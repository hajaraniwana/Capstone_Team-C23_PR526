package com.zenith.ecoscan.screen.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zenith.ecoscan.R
import com.zenith.ecoscan.databinding.FragmentFormBinding

class FormFragment : Fragment() {
    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!
    private val firstOptions = arrayOf("YES", "NO")
    private var secondOptions = arrayOf<String>()
    private var thirdOptions = arrayOf<String>()
    private var fourthOptions = arrayOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDropdown(binding.firstDropdown, firstOptions)
        setDropdown(binding.secondDropdown, secondOptions)
        setDropdown(binding.thirdDropdown, thirdOptions)
        setDropdown(binding.fourthDropdown, fourthOptions)

    }

    private fun <T> setDropdown(dropdown: Spinner, options: Array<T>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, options)
        dropdown.adapter = adapter

        dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedOption = options[position]
                Toast.makeText(requireContext(), "Pilihan Dropdown: $selectedOption", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}