package org.android.go.sopt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.android.go.sopt.databinding.ActivityIntroduceBinding

class IntroduceActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityIntroduceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityIntroduceBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        Toast.makeText(
            applicationContext,
            "로그인 성공!",
            Toast.LENGTH_SHORT
        ).show()

        viewBinding.introduceTvName.text = viewBinding.introduceTvName.text.toString() + intent.getStringExtra("name")
        viewBinding.introduceTvSp.text = viewBinding.introduceTvSp.text.toString() + intent.getStringExtra("special")


    }
}