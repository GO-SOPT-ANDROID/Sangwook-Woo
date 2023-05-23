package org.android.go.sopt.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import org.android.go.sopt.MainActivity
import org.android.go.sopt.ServicePool
import org.android.go.sopt.data.RequestLoginDto
import org.android.go.sopt.data.ResponseLoginDto
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.signup.SignupActivity
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater)}
    private val viewModel by viewModels<LoginViewModel>()
    var name: String? = null
    var id: String? = null
    var skill: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLoginpageSignup.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }


        binding.btnLoginpageLogin.setOnClickListener {
            viewModel.logIn(
                binding.etLoginpageLogin.text.toString(),
                binding.etLoginpagePassword.text.toString()
            )
        }

        viewModel.loginResult.observe(this) { loginResult ->
            startActivity(
                Intent(
                    this@LoginActivity,
                    MainActivity::class.java
                )
            )
            Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
        }
    }
    private val loginService = ServicePool.loginService
    private fun completeLogin(mintent: Intent) {
        loginService.login(
            with(binding) {
                RequestLoginDto(
                    etLoginpageLogin.text.toString(),
                    etLoginpagePassword.text.toString(),
                )
            }
        ).enqueue(object : retrofit2.Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.message?.let { Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show(); }

                    if (!isFinishing) {
                        mintent.putExtra("name",response.body()?.data?.name)
                        mintent.putExtra("id",response.body()?.data?.id)
                        mintent.putExtra("skill",response.body()?.data?.skill)
                        startActivity(mintent)
                    }
                } else {
                    response.body()?.message?.let { Toast.makeText(getApplicationContext(), "서버통신 실패(40X)", Toast.LENGTH_SHORT).show(); }
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                t.message?.let { Toast.makeText(getApplicationContext(), "서버통신 실패(응답값 X)", Toast.LENGTH_SHORT).show(); }
            }
        })
    }
}