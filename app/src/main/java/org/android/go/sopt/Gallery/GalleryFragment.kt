package org.android.go.sopt.Gallery

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import org.android.go.sopt.Adapter.HeaderAdapter
import org.android.go.sopt.Adapter.ItemAdapter
import org.android.go.sopt.Adapter.MusicAdapter
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding){"_binding이 null!"}

    val viewModel: GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MusicAdapter(requireContext())

        viewModel.completeGetMusic()
        viewModel.musicListResult.observe(viewLifecycleOwner) {musicListResult->
            if(musicListResult.data != null){
                adapter.submitList(musicListResult.data.musicList)
            }else{
                Log.e("test","empty userList")
            }

        }

        binding.rvGallery.adapter = adapter

        binding.btnAddMusic.setOnClickListener {
            val intent = Intent(context,ImagePickerActivity::class.java)
            startActivity(intent)
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