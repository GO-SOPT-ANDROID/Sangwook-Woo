package org.android.go.sopt.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import org.android.go.sopt.Adapter.HeaderAdapter
import org.android.go.sopt.Adapter.ItemAdapter
import org.android.go.sopt.ServicePool
import org.android.go.sopt.data.ResponseListUsersDto
import org.android.go.sopt.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
    get() = requireNotNull(_binding){"_binding이 null!"}

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter1 = ItemAdapter(requireContext())
        val adapter2 = HeaderAdapter(requireContext())
        val adapter3 = ConcatAdapter(adapter2,adapter1)

        viewModel.completeGetUsers()
        viewModel.userListResult.observe(viewLifecycleOwner) {userListResult->
            if(userListResult.data != null){
                adapter1.submitList(userListResult.data)
            }else{
                Log.e("test","empty userList")
            }

        }
        adapter2.setHeaderText("유저 정보")
        binding.rvHome.adapter = adapter3
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    companion object{
        fun newInstance(): HomeFragment {
           return HomeFragment()
        }
    }
}