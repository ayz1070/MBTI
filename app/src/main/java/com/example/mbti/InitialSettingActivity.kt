package com.example.mbti

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.mbti.databinding.ActivityInitialSettingBinding

class InitialSettingActivity : AppCompatActivity() {
    lateinit var binding: ActivityInitialSettingBinding
    lateinit var name:String
    lateinit var num:String
    var age:Int = 0
    private var gender : Int = 0
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialSettingBinding.inflate(layoutInflater)
        sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            name = binding.etName.text.toString()
            num = binding.etAge.text.toString()
            age = num.toInt()
            Log.d("초기 설정 액티비티", "이름: ${name}, 나이 ${age}, 성별: ${gender}")
            sharedPref.edit().run{
                putString("name",name)
                putInt("age",age)
                commit()
            }
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.ivProfile.setOnClickListener {
            val requestLauncher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            )
            {
                Glide.with(this)
                    .load(it.data!!.data)
                    .into(binding.ivProfile)
            }
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestLauncher.launch(intent)
        }

    }

}