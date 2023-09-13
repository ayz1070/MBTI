package com.example.mbti

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.mbti.databinding.ActivityCommunityDetailBinding
import com.example.mbti.model.Post
import java.io.Serializable

class CommunityDetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCommunityDetailBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getSerializableExtra("post",Post::class.java)


        binding.run{
            tvContent.text = data?.content
            tvDate.text = data?.date
            tvMbti.text = data?.mbti
            tvNickname.text=data?.nickname
            tvTitle.text=data?.title

        }
    }
}