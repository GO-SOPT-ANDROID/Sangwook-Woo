package org.android.go.sopt.Gallery

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import coil.load
import org.android.go.sopt.body.ContentUriRequestBody
import org.android.go.sopt.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding){"_binding이 null!"}
    private val viewModel : GalleryViewModel by viewModels()

    private val launcher = registerForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(maxItems = 3)
    ) { imageList : List<Uri> ->
        viewModel.setRequestBody(ContentUriRequestBody(requireContext(), imageList[0]))
        binding.ivGalleryFirst.load(imageList[0])
        viewModel.uploadProfileImage()
    }
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(requireContext(),"허가", Toast.LENGTH_SHORT)
        } else {
            Toast.makeText(requireContext(),"불허", Toast.LENGTH_SHORT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGalleryPickImage.setOnClickListener {
            requestPermissionLauncher.launch("android.permission.ACCESS_COARSE_LOCATION")
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    companion object{
        fun newInstance(): GalleryFragment{
            return GalleryFragment()
        }
    }
}