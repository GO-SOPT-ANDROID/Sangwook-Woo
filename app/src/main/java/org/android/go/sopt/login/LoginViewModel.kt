package org.android.go.sopt.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.ServicePool.loginService
import org.android.go.sopt.data.RequestLoginDto
import org.android.go.sopt.data.ResponseLoginDto
import org.android.go.sopt.data.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel(){
    private val _loginResult: MutableLiveData<ResponseLoginDto> = MutableLiveData()
    val loginResult: LiveData<ResponseLoginDto> = _loginResult

    fun logIn(id: String, password: String){
        loginService.login(
            RequestLoginDto(
                id,
                password
            )
        ).enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>
            ) {
                if(response.isSuccessful){
                    _loginResult.value = response.body()
                }else{
                    TODO("Not yet implemented")
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}