package org.android.go.sopt.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.ServicePool
import org.android.go.sopt.data.RequestSignUpDto
import org.android.go.sopt.data.ResponseSignUpDto
import org.android.go.sopt.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Response


class SignupActivity : AppCompatActivity(){
    private lateinit var viewBinding: ActivitySignupBinding
    val viewModel: SignupViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.etId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.checkId(s.toString())
                val editText1Able = !viewBinding.etId.text.isNullOrEmpty()&& viewBinding.etId.text.toString().matches(Regex("[a-zA-Z0-9]{6,10}"))
                val editText2Able = !viewBinding.etPassword.text.isNullOrEmpty() && viewBinding.etPassword.text.toString().matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,12}$"))
                val editText3Able = !viewBinding.etName.text.isNullOrEmpty()
                val editText4Able = !viewBinding.etSkill.text.isNullOrEmpty()

                viewBinding.btnSignuppageComplete.isEnabled =
                    (editText1Able && editText2Able && editText3Able && editText4Able)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        viewBinding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.checkPw(s.toString())
                val editText1Able = !viewBinding.etId.text.isNullOrEmpty()&& viewBinding.etId.text.toString().matches(Regex("[a-zA-Z0-9]{6,10}"))
                val editText2Able = !viewBinding.etPassword.text.isNullOrEmpty() && viewBinding.etPassword.text.toString().matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,12}$"))
                val editText3Able = !viewBinding.etName.text.isNullOrEmpty()
                val editText4Able = !viewBinding.etSkill.text.isNullOrEmpty()

                viewBinding.btnSignuppageComplete.isEnabled =
                    (editText1Able && editText2Able && editText3Able && editText4Able)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        viewBinding.etName.addTextChangedListener(EditTextCheck)
        viewBinding.etSkill.addTextChangedListener(EditTextCheck)

        viewModel.idCheck.observe(this) { idCheck ->
            if (idCheck) {
                viewBinding.layoutId.isErrorEnabled = false
            } else {
                viewBinding.layoutId.isErrorEnabled = true
                viewBinding.layoutId.error = "영문, 숫자가 포함되어야 하고 6~10글자 이내로 입력해야합니다"
            }

        }

        viewModel.pwCheck.observe(this) { pwCheck ->
            if (pwCheck) {
                viewBinding.layoutPassword.isErrorEnabled = false
                Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
            } else {
                viewBinding.layoutPassword.isErrorEnabled = true
                viewBinding.layoutPassword.error = "영문, 숫자, 특수문자가 포함되어야 하고 6~12글자 이내로 입력해야합니다"
            }

        }

        viewModel.signUpResult.observe(this, { result->
            finish()
            Toast.makeText(this,result.message,Toast.LENGTH_SHORT).show()
        })

        viewModel.errorResult.observe(this, { result->
            Toast.makeText(this,result.message,Toast.LENGTH_SHORT).show()
        })

        viewBinding.btnSignuppageComplete.setOnClickListener {
            viewModel.completeSignUp(
                viewBinding.etId.text.toString(),
                viewBinding.etPassword.text.toString(),
                viewBinding.etSkill.text.toString(),
                viewBinding.etName.text.toString()
            )
        }
    }

    private val signUpService = ServicePool.signUpService

    private val EditTextCheck = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            val editText1Able = !viewBinding.etId.text.isNullOrEmpty()&& viewBinding.etId.text.toString().matches(Regex("[a-zA-Z0-9]{6,10}"))
            val editText2Able = !viewBinding.etPassword.text.isNullOrEmpty() && viewBinding.etPassword.text.toString().matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,12}$"))
            val editText3Able = !viewBinding.etName.text.isNullOrEmpty()
            val editText4Able = !viewBinding.etSkill.text.isNullOrEmpty()

            viewBinding.btnSignuppageComplete.isEnabled =
                (editText1Able && editText2Able && editText3Able && editText4Able)
        }
    }

    private fun completeSignUp() {
        signUpService.signup(
            with(viewBinding) {
                RequestSignUpDto(
                    etId.text.toString(),
                    etPassword.text.toString(),
                    etSkill.text.toString(),
                    etName.text.toString()
                )
            }
        ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.message?.let {
                        Toast.makeText(
                            getApplicationContext(),
                            "회원가입에 성공했습니다.",
                            Toast.LENGTH_SHORT
                        ).show();
                    }

                    if (!isFinishing) finish()
                } else {
                    response.body()?.message?.let {
                        Toast.makeText(
                            getApplicationContext(),
                            "서버통신 실패(40X)",
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                t.message?.let {
                    Toast.makeText(
                        getApplicationContext(),
                        "서버통신 실패(응답값 X)",
                        Toast.LENGTH_SHORT
                    ).show();
                }
            }
        })
    }
}