package com.example.mbti

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mbti.databinding.FragmentCommunityBinding


class CommunityFragment : Fragment() {
    lateinit var binding:FragmentCommunityBinding
    lateinit var intent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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


}