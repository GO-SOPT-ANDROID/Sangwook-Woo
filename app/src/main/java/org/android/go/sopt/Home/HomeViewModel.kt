package org.android.go.sopt.Home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.Adapter.ItemAdapter
import org.android.go.sopt.ServicePool
import org.android.go.sopt.data.ResponseListUsersDto
import retrofit2.Call
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _userListResult: MutableLiveData<ResponseListUsersDto> = MutableLiveData()
    val userListResult: LiveData<ResponseListUsersDto> = _userListResult

    private val getListUsersService = ServicePool.GetListUsersService
    fun completeGetUsers() {
        getListUsersService.getusers().enqueue(object : retrofit2.Callback<ResponseListUsersDto> {
            override fun onResponse(
                call: Call<ResponseListUsersDto>,
                response: Response<ResponseListUsersDto>,
            ) {
                if (response.isSuccessful) {
                    _userListResult.value = response.body()
                    Log.e("test", "서버통신 성공")

                } else {
                    Log.e("test", "서버통신 실패 404")
                }
            }

            override fun onFailure(call: Call<ResponseListUsersDto>, t: Throwable) {
                Log.e("test", "서버통신 실패 응답값X")
            }
        })
    }
}