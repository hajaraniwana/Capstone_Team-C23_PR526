package com.zenith.ecoscan.screen.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zenith.ecoscan.databinding.FragmentSplashBinding
import kotlinx.coroutines.*
import kotlin.random.Random

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding
    private lateinit var progressJob: Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressJob = CoroutineScope(Dispatchers.Main).launch {
            var progress = 0
            val random = Random
            val randomIncrement = random.nextInt(10, 16)

            while (progress < 100) {
                delay(500)
                progress += randomIncrement
                binding?.progressBar?.progress = progress
            }
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        progressJob.cancel()
        _binding = null
    }

}