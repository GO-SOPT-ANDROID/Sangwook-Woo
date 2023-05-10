package org.android.go.sopt.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.MainActivity
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.signup.SignupActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name = intent.getStringExtra("name")
        var special = intent.getStringExtra("special")
        var identity = intent.getStringExtra("identity")
        var password = intent.getStringExtra("password")

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val data: Intent? = result.data
                name = data?.getStringExtra("name")
                special = data?.getStringExtra("special")
                identity = data?.getStringExtra("identity")
                password = data?.getStringExtra("password")
                Snackbar.make(
                    binding.root,
                    "회원가입을 완료했습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnLoginpageSignup.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            resultLauncher.launch(intent)
        }

        binding.btnLoginpageLogin.setOnClickListener {
            if(binding.etLoginpageLogin.text.toString().equals(identity)&&binding.etLoginpagePassword.text.toString().equals(password)){
                val mintent = Intent(this, MainActivity::class.java)
                mintent.putExtra("name",name)
                mintent.putExtra("special",special)
                startActivity(mintent)
            }else if(binding.etLoginpageLogin.text.toString().equals(identity)&&!(binding.etLoginpagePassword.text.toString().equals(password))){
                Snackbar.make(
                    binding.root,
                    "비밀번호가 틀렸습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }else{
                Snackbar.make(
                    binding.root,
                    "아이디가 틀렸습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}