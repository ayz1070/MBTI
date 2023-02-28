package com.example.mbti

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mbti.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var sharedPref:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        setContentView(binding.root)

        val name = sharedPref.getString("name","")
        val age = sharedPref.getInt("age",0)

        binding.nameTv.text = name
        binding.tvAge.text = age.toString()

        binding.nameTv.setOnClickListener{

        }
    }
}