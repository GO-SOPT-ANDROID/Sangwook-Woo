package org.android.go.sopt.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private val mockItemList: List<ResponseListUsersDto.UserData> = emptyList()

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

        this.completeGetUsers(adapter1)
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
    private val getListUsersService = ServicePool.GetListUsersService
    private fun completeGetUsers(adapter: ItemAdapter) {
        getListUsersService.getusers().enqueue(object : retrofit2.Callback<ResponseListUsersDto> {
            override fun onResponse(
                call: Call<ResponseListUsersDto>,
                response: Response<ResponseListUsersDto>,
            ) {
                if (response.isSuccessful) {
                    val userList = response.body()?.data
                    if (userList != null) {
                        adapter.setItemList(userList)
                    } else {
                        Toast.makeText(context, "사용자 목록이 비어 있습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "서버통신 실패(40X)", Toast.LENGTH_SHORT).show();
                }
            }

            override fun onFailure(call: Call<ResponseListUsersDto>, t: Throwable) {
                t.message?.let { Toast.makeText(context, "서버통신 실패(응답값 X)", Toast.LENGTH_SHORT).show(); }
            }
        })
    }
}