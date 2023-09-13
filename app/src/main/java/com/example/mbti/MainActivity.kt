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
        setContentView(binding.root)

        binding.navBottom.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when(it.itemId){
                R.id.nav_home -> transaction.replace(R.id.fl_main, HomeFragment())
                R.id.nav_community -> transaction.replace(R.id.fl_main, CommunityFragment())
                R.id.nav_setting -> transaction.replace(R.id.fl_main,SettingFragment())
            }
            transaction.commit()
            true
        }

    }
}