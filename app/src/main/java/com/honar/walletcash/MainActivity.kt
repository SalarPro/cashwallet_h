package com.honar.walletcash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this,MainMenuActivity::class.java))
        finish()
        confirmCode.setOnClickListener {
            if (accessCodeET.text.toString() == "123"){
                startActivity(Intent(this,MainMenuActivity::class.java))
                finish()
            }
        }

    }
}
