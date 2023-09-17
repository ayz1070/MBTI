package com.example.mbti

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mbti.databinding.FragmentCommunityBinding
import com.example.mbti.model.Post
import com.example.mbti.recycler.CommunityAdapter
import com.google.firebase.firestore.ktx.toObject


class CommunityFragment : Fragment() {
    lateinit var binding:FragmentCommunityBinding
    lateinit var intent: Intent
    lateinit var posts: MutableList<Post>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        posts = mutableListOf<Post>()
        makeRecyclerView()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        binding.btnWrite.setOnClickListener{
            intent = Intent(context,WriteActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }
    fun makeRecyclerView(){
        MyApplication.db.collection("posts")
            .orderBy("timestamp")
            .get()
            .addOnSuccessListener { result->
                for(document in result){
                    val post = document.toObject(Post::class.java)
                    posts.add(post)
                    Log.d("글 목록","${post.title}")
                }
                binding.rvBoard.layoutManager = LinearLayoutManager(requireContext())
                binding.rvBoard.adapter = CommunityAdapter(requireContext(),posts)
                binding.rvBoard.addItemDecoration(DividerItemDecoration(requireContext(),1))
                Log.d("서버 전송","서버 전송 성공")
            }
            .addOnFailureListener{e ->
                Log.d("서버 전송","서버 전송 실패",e)
                Toast.makeText(context,"서버로부터 데이터 획득 실패",Toast.LENGTH_SHORT).show()
            }
    }

}