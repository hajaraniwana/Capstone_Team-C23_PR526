package com.zenith.ecoscan.screen.form

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zenith.ecoscan.R
import com.zenith.ecoscan.databinding.FragmentFormBinding
import com.zenith.ecoscan.utils.overlayLoading

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

        binding.submitButton.setOnClickListener { uploadData() }

        playAnimation()
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

    private fun uploadData() {
        overlayLoading(binding.progressOverlay, true)
    }

    private fun playAnimation() {
        animationText(binding.tvFirstDropdown)
        animationText(binding.tvSecondDropdown)
        animationText(binding.tvThirdDropdown)
        animationText(binding.tvFourthDropdown)

        animationSpinner(binding.firstDropdown)
        animationSpinner(binding.secondDropdown)
        animationSpinner(binding.thirdDropdown)
        animationSpinner(binding.fourthDropdown)

        val image = ObjectAnimator.ofFloat(binding.ivFormImage, View.ALPHA, 1f).setDuration(500)
        val button = ObjectAnimator.ofFloat(binding.submitButton, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playTogether(image, button)
            start()
        }
    }

    private fun animationText(textView: TextView) {
        ObjectAnimator.ofFloat(textView, View.TRANSLATION_X, -1000f, 0f).apply {
            duration = 500
        }.start()
    }

    private fun animationSpinner(spinner: Spinner) {
        ObjectAnimator.ofFloat(spinner, View.TRANSLATION_X, 1000f, 0f).apply {
            duration = 500
        }.start()
    }
}