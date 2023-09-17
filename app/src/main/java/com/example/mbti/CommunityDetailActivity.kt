package com.example.mbti

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mbti.databinding.ActivityCommunityDetailBinding
import com.example.mbti.model.Comment
import com.example.mbti.model.Post
import com.example.mbti.recycler.CommentAdapter
import com.example.mbti.recycler.CommentDecoration
import com.example.mbti.recycler.CommunityAdapter
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date

// 남은 것
// 1. 리사이클러 뷰 생성 시 이미 담겨있던 내용까지 중복됨
// 2. UI 설정
// 3.

class CommunityDetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCommunityDetailBinding
    private lateinit var comment:Comment
    private lateinit var content:String
    private lateinit var commentList:MutableList<Comment>
    private lateinit var post: Post

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        commentList = mutableListOf<Comment>()

        post = intent.getSerializableExtra("post",Post::class.java)!!
        Log.d("데이터","post from intent : ${post.docId}")

        makeRecyclerView()

        binding.run{
            tvContent.text = post?.content
            tvDate.text = post?.timestamp
            tvMbti.text = post?.mbti
            tvNickname.text=post?.nickname
            tvTitle.text=post?.title
        }

        binding.btnComment.setOnClickListener{
            content = binding.etComment.text.toString()
            comment = Comment(
                MyApplication.nickname,
                MyApplication.mbti,
                content,
                MyApplication.email,
                dateToString(Date()),
                post?.title
            )
            MyApplication.db.collection("comments")
                .add(comment)
                .addOnSuccessListener {

                }
                .addOnFailureListener{

                }

            makeRecyclerView()
        }

    }
    fun dateToString(date: Date): String? {
        val format = SimpleDateFormat("yy년 MM월 dd일 HH:mm" )
        return format.format(date)
    }

    fun makeRecyclerView(){
        commentList.clear()
        MyApplication.db.collection("comments")
            .orderBy("timestamp")
            .get()
            .addOnSuccessListener { result->
                for(document in result){
                    val comment = document.toObject(Comment::class.java)
                    if(comment.docId == post?.docId) {
                        commentList.add(comment)
                        Log.d("데이터","post, comment: ${post.docId}, ${comment.docId}")
                        Log.d("글 목록","${comment.content}")
                    }

                }
                binding.rvComment.layoutManager = LinearLayoutManager(this)
                binding.rvComment.adapter = CommentAdapter(this,commentList)
                binding.rvComment.addItemDecoration(DividerItemDecoration(this,1))

                Log.d("서버 전송","서버 전송 성공")
            }
            .addOnFailureListener{e ->
                Log.d("서버 전송","서버 전송 실패",e)
                Toast.makeText(this,"서버로부터 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }
    }
}