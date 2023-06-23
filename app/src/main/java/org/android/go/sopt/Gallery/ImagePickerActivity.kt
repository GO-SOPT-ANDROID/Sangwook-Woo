package org.android.go.sopt.Gallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import coil.load
import org.android.go.sopt.databinding.ActivityImagePickerBinding
import org.android.go.sopt.util.ContentUriRequestBody

class ImagePickerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImagePickerBinding
    val viewModel : ImagePickerViewModel by viewModels()

    val launcher = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { it->
        binding.ivImage.load(it)
        viewModel.setRequestBody(ContentUriRequestBody(this, it!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnImageSelector.setOnClickListener {
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }

        binding.btnRegister.setOnClickListener {
            upload()
        }
    }

    fun upload(){
        viewModel.uploadMusicInfo(binding.etTitle.text.toString(), binding.etSinger.text.toString())
        finish()
    }
}