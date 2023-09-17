package com.example.mbti.recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mbti.R
import com.example.mbti.databinding.ItemCommentBinding
import com.example.mbti.model.Comment
import com.example.mbti.model.Post


class CommentViewHolder(val binding:ItemCommentBinding, val itemList:MutableList<Comment>): RecyclerView.ViewHolder(binding.root)
class CommentAdapter(val context: Context, val itemList:MutableList<Comment>):RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CommentViewHolder(ItemCommentBinding.inflate(layoutInflater),itemList)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = itemList.get(position)

        holder.binding.run{
            tvNickname.text = comment.nickname
            tvComment.text = comment.content
            tvMbti.text = comment.mbti
            tvTime.text=comment.timestamp.toString()
        }

    }
}

class CommentDecoration(val context:Context):RecyclerView.ItemDecoration(){
    private val divider: Drawable? = context.getDrawable(R.drawable.divider)
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + (divider?.intrinsicHeight ?: 0)

            divider?.let {
                it.setBounds(left, top, right, bottom)
                it.draw(c)
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
    }
}