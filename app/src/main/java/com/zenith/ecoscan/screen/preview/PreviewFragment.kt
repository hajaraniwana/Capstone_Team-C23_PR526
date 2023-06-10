package com.zenith.ecoscan.screen.preview

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zenith.ecoscan.R
import com.zenith.ecoscan.data.api.response.Item
import com.zenith.ecoscan.databinding.FragmentPreviewBinding
import com.zenith.ecoscan.utils.overlayLoading
import com.zenith.ecoscan.utils.uriToFile
import java.io.File
import com.zenith.ecoscan.utils.Result
import com.zenith.ecoscan.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewFragment : Fragment() {

    private var _binding: FragmentPreviewBinding? = null
    private val binding get() = _binding!!
    private val args: PreviewFragmentArgs by navArgs()
    private var imageReady: File? = null
    private val previewViewModel: PreviewViewModel by viewModels()

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
        imageReady = uriToFile(uri, requireContext())

        if (selectedImageUri.isNotEmpty()) {
            binding.ivPreview.setImageURI(uri)
        }
    }

    private fun uploadData() {

        if (imageReady != null) print(true) else print(false)

        previewViewModel.uploadPhoto(imageReady!!).observe(viewLifecycleOwner) { response ->

            when (response) {
                is Result.Loading -> overlayLoading(binding.progressOverlay, true)

                is Result.Success -> {
                    val item = response.data.item
                    val dataNextScreen = Item(
                        item.averageEnergy,
                        item.dampakDisposal,
                        item.dampakProduksi,
                        item.name,
                        item.dampakKonsumsi,
                        item.image,
                        item.linkSumber
                    )
                    findNavController().navigate(
                        PreviewFragmentDirections.actionPreviewFragmentToDetailFragment(
                            dataNextScreen
                        )
                    )
                }

                is Result.Error -> {
                    showToast(requireContext(), getString(R.string.error), response.error)
                    findNavController().navigate(PreviewFragmentDirections.actionPreviewFragmentToHomeFragment())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}