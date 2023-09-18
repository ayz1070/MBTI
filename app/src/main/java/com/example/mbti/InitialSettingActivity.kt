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
import com.example.mbti.model.User

class InitialSettingActivity : AppCompatActivity() {
    lateinit var binding: ActivityInitialSettingBinding
    lateinit var nickname:String
    lateinit var num:String
    var age:Int = 0
    lateinit var mbti:String
    // 0은 남자, 1은 여자로 설정함
    private var gender : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialSettingBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            nickname = binding.etName.text.toString()
            num = binding.etAge.text.toString()
            age = num.toInt()
            mbti = binding.etMbti.text.toString()

            MyApplication.nickname = nickname
            MyApplication.mbti = mbti
            MyApplication.gender = gender
            MyApplication.age = age
            MyApplication.user = User(nickname,MyApplication.email,age,gender,mbti)
            MyApplication.db.collection("users")
                .add(MyApplication.user!!)
                .addOnSuccessListener { documentReference ->
                    Log.d("데이터베이스", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("데이터베이스", "Error adding document", e)
                }
            startActivity(Intent(this,MainActivity::class.java))
        }

//        binding.ivProfile.setOnClickListener {
//            val requestLauncher = registerForActivityResult(
//                ActivityResultContracts.StartActivityForResult()
//            )
//            {
//                Glide.with(this)
//                    .load(it.data!!.data)
//                    .into(binding.ivProfile)
//            }
//            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            intent.type = "image/*"
//            requestLauncher.launch(intent)
//        }

        binding.rgGender.setOnCheckedChangeListener{_,checkedId ->
            when(checkedId){
                R.id.btn_man -> { gender = 0}
                R.id.btn_woman -> { gender = 1}
            }
        }

    }

}