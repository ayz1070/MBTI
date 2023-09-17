package com.example.mbti.recycler

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mbti.CommunityDetailActivity
import com.example.mbti.databinding.ItemRecyclerviewBinding
import com.example.mbti.model.Post

class CommunityViewHolder(val binding:ItemRecyclerviewBinding,val itemList:MutableList<Post>):RecyclerView.ViewHolder(binding.root){
    init {
        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val selectedItem = itemList[position]
                // 상세 페이지로 데이터 전달 및 페이지 열기
                val intent = Intent(itemView.context, CommunityDetailActivity::class.java)
                intent.putExtra("post", selectedItem)
                itemView.context.startActivity(intent)
            }
        }
    }

}


class CommunityAdapter(val context: Context, val itemList:MutableList<Post>):
    RecyclerView.Adapter<CommunityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CommunityViewHolder(ItemRecyclerviewBinding.inflate(layoutInflater),itemList)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        val post = itemList.get(position)

        holder.binding.run {
            tvTitle.text = post.title
            tvTime.text = post.timestamp
            tvMbti.text = post.mbti
        }
    }

}