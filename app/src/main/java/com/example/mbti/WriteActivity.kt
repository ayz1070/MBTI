package com.example.mbti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mbti.databinding.ActivityWriteBinding
import com.example.mbti.model.Post
import java.text.SimpleDateFormat
import java.util.Date

class WriteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityWriteBinding
    private lateinit var title:String
    private lateinit var content:String
    private lateinit var post: Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnComplete.setOnClickListener{
            title = binding.etTitle.text.toString()
            content = binding.etContent.text.toString()
            post = Post(content,dateToString(Date()),MyApplication.email,MyApplication.mbti,MyApplication.nickname,title)
            MyApplication.db.collection("posts")
                .add(post)
                .addOnSuccessListener {
                    Toast.makeText(this,"업로드 성공",Toast.LENGTH_SHORT)
                }
                .addOnFailureListener{
                    Toast.makeText(this,"업로드 실패",Toast.LENGTH_SHORT)
                }

        }
    }

    override fun onStart() {
        super.onStart()

    }
    fun dateToString(date: Date): String? {
        val format = SimpleDateFormat("yy년 MM월 dd일 HH:mm" )
        return format.format(date)
    }
}

