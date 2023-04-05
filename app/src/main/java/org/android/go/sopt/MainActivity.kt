package org.android.go.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)

        var n = intent.getStringExtra("name")
        var s = intent.getStringExtra("special")
        var i = intent.getStringExtra("identity")
        var p = intent.getStringExtra("password")

        setContentView(viewBinding.root)

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){result ->
            if(result.resultCode == RESULT_OK){
                val data: Intent? = result.data
                val name = data?.getStringExtra("name")
                val special = data?.getStringExtra("special")
                val identity = data?.getStringExtra("identity")
                val password = data?.getStringExtra("password")
                n = name
                s = special
                i = identity
                p = password
                Snackbar.make(
                    viewBinding.root,
                    "회원가입을 완료했습니다."+identity+password,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        viewBinding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            resultLauncher.launch(intent)
        }

        viewBinding.btnLogin.setOnClickListener {
            if(viewBinding.etId.text.toString().equals(i)&&viewBinding.etPw.text.toString().equals(p)){
                val mintent = Intent(this,IntroduceActivity::class.java)
                mintent.putExtra("name",n)
                mintent.putExtra("special",s)
                startActivity(mintent)
            }else if(viewBinding.etId.text.toString().equals(i)&&!(viewBinding.etPw.text.toString().equals(p))){
                Snackbar.make(
                    viewBinding.root,
                    "비밀번호가 틀렸습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }else{
                Snackbar.make(
                    viewBinding.root,
                    "아이디가 틀렸습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }



    }

}