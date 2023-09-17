package com.example.mbti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mbti.databinding.ActivityBaseBinding

open class BaseActivity : AppCompatActivity() {
    lateinit var baseBinding:ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)

        baseBinding.btnBack.setOnClickListener{
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}