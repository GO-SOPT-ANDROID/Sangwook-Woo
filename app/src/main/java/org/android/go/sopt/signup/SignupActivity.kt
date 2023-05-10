package org.android.go.sopt.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.ServicePool
import org.android.go.sopt.data.RequestSignUpDto
import org.android.go.sopt.data.ResponseSignUpDto
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.login.LoginActivity
import retrofit2.Call
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnSignuppageComplete.setOnClickListener {
            completeSignUp()
        }
    }
    private val signUpService = ServicePool.signUpService

    private fun completeSignUp() {
        signUpService.login(
            with(viewBinding) {
                RequestSignUpDto(
                    etSignuppagePutid.text.toString(),
                    etSignuppagePutpassword.text.toString(),
                    etSignuppagePutspecial.text.toString(),
                    etSignuppagePutname.text.toString()
                )
            }
        ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.message?.let { Toast.makeText(getApplicationContext(), "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show(); }

                    if (!isFinishing) finish()
                } else {
                    // 실패한 응답
                    response.body()?.message?.let { Toast.makeText(getApplicationContext(), "서버통신 실패(40X)", Toast.LENGTH_SHORT).show(); }
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                // 왜 안 오노
                t.message?.let { Toast.makeText(getApplicationContext(), "서버통신 실패(응답값 X)", Toast.LENGTH_SHORT).show(); }
            }
        })
    }
}