package org.android.go.sopt.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.ServicePool
import org.android.go.sopt.data.RequestSignUpDto
import org.android.go.sopt.data.ResponseSignUpDto
import org.android.go.sopt.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Response


class SignupActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySignupBinding
    var idInput = false
    var pwInput = false
    var nameInput = false
    var skillInput = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.etSignuppagePutid.addTextChangedListener(EditTextCheck)
        viewBinding.etSignuppagePutpassword.addTextChangedListener(EditTextCheck)
        viewBinding.etSignuppagePutname.addTextChangedListener(EditTextCheck)
        viewBinding.etSignuppagePutskill.addTextChangedListener(EditTextCheck)

        viewBinding.btnSignuppageComplete.setOnClickListener {
            completeSignUp()
        }
    }
    private val signUpService = ServicePool.signUpService

    private val EditTextCheck = object : TextWatcher{
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            val editText1HasText = !viewBinding.etSignuppagePutid.text.isNullOrEmpty()
            val editText2HasText = !viewBinding.etSignuppagePutpassword.text.isNullOrEmpty()
            val editText3HasText = !viewBinding.etSignuppagePutname.text.isNullOrEmpty()
            val editText4HasText = !viewBinding.etSignuppagePutskill.text.isNullOrEmpty()

            // 모든 EditText가 입력되었다면 버튼 활성화, 아니면 비활성화
            viewBinding.btnSignuppageComplete.isEnabled= (editText1HasText && editText2HasText && editText3HasText && editText4HasText)
        }
    }
    private fun completeSignUp() {
        signUpService.login(
            with(viewBinding) {
                RequestSignUpDto(
                    etSignuppagePutid.text.toString(),
                    etSignuppagePutpassword.text.toString(),
                    etSignuppagePutskill.text.toString(),
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