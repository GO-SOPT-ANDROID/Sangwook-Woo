package org.android.go.sopt.signup

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.ServicePool
import org.android.go.sopt.data.RequestSignUpDto
import org.android.go.sopt.data.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Response

class SignupViewModel : ViewModel() {
    private val _signUpResult : MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val signUpResult : LiveData<ResponseSignUpDto> = _signUpResult

    private val _errorResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val errorResult: LiveData<ResponseSignUpDto> = _errorResult

    private val signUpService = ServicePool.signUpService

    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val skill = MutableLiveData<String>()

    val idCheck = MutableLiveData<Boolean>()
    val pwCheck = MutableLiveData<Boolean>()

    fun checkId(id: String){
        if(id.isNullOrEmpty() || id!!.matches(Regex("[a-zA-Z0-9]{6,10}"))){
            idCheck.value = true
        }else{
            idCheck.value = false
        }
    }

    fun checkPw(password: String){
        if(password.isNullOrEmpty() || password!!.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,12}$"))){
            pwCheck.value = true
        }else{
            pwCheck.value = false
        }
    }

    fun completeSignUp(id: String, password: String, skill: String, name: String) {
        signUpService.signup(
            RequestSignUpDto(
                id,
                password,
                skill,
                name
            )
        ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    _signUpResult.value = response.body()
                    Log.e("sangwook","success")
                } else {
                    _errorResult.value = response.body()
                    Log.e("sangwook","fail")
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                Log.e("sangwook","failure")
            }
        })
    }
}