package com.example.mbti

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
            post = Post(title,content,dateToString(Date()),MyApplication.nickname,MyApplication.email,MyApplication.mbti)
            MyApplication.db.collection("Posts")
                .add(post)
                .addOnSuccessListener {
                    Toast.makeText(this,"업로드 성공",Toast.LENGTH_SHORT)
                }
                .addOnFailureListener{
                    Toast.makeText(this,"업로드 실패",Toast.LENGTH_SHORT)
                }
        }
    }
    fun dateToString(date: Date): String? {
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }
}

