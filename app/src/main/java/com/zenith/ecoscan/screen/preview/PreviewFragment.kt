package com.zenith.ecoscan.screen.preview

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zenith.ecoscan.databinding.FragmentPreviewBinding
import com.zenith.ecoscan.utils.overlayLoading
import java.io.File

class PreviewFragment : Fragment() {

    private var _binding: FragmentPreviewBinding? = null
    private val binding get() = _binding!!
    private val args: PreviewFragmentArgs by navArgs()
    private var imageReady: File? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPreviewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancel.setOnClickListener {
            findNavController().navigate(PreviewFragmentDirections.actionPreviewFragmentToHomeFragment())
        }
        
        binding.btnAccept.setOnClickListener { uploadData() }

        showImage()
    }

    private fun showImage() {
        val selectedImageUri: String = args.uri
        val uri = Uri.parse(selectedImageUri)

        if (selectedImageUri.isNotEmpty()) {
            binding.ivPreview.setImageURI(uri)
        }
    }

    private fun uploadData() {
        overlayLoading(binding.progressOverlay, true)
        findNavController().navigate(PreviewFragmentDirections.actionPreviewFragmentToDetailFragment(args.uri))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}