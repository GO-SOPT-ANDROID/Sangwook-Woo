package org.android.go.sopt.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.login.LoginActivity

class SignupActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnSignuppageComplete.setOnClickListener {
            val name = viewBinding.etSignuppagePutname.text.toString()
            val special = viewBinding.etSignuppagePutspecial.text.toString()
            val identity = viewBinding.etSignuppagePutid.text.toString()
            val password = viewBinding.etSignuppagePutpassword.text.toString()
            val intent = Intent(applicationContext, LoginActivity::class.java)
            if(identity.length < 6 || identity.length>10){
                Snackbar.make(
                    viewBinding.root,
                    "아이디는 6자~10자 사이로 입력해주세요.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }else if(password.length < 8 || password.length > 12){
                Snackbar.make(
                    viewBinding.root,
                    "비밀번호는 8자~12자 사이로 입력해주세요.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }else if(identity.equals("") || special.equals("")){
                Snackbar.make(
                    viewBinding.root,
                    "이름과 특기를 입력해주세요.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }else{
                intent.putExtra("name",name)
                intent.putExtra("special",special)
                intent.putExtra("identity",identity)
                intent.putExtra("password", password)
                setResult(RESULT_OK,intent)
                finish()
            }
        }
    }
}