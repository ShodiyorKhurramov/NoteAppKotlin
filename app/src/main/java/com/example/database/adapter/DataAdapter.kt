package com.example.database.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.database.R
import com.example.database.manager.RealmManager
import com.example.database.model.Post


class DataAdapter(var context: Context, var items: ArrayList<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setIntemList(post: Post){
        RealmManager.instance!!.savePost(post)
        items= RealmManager.instance!!.loadPosts()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_lists, parent, false)
        return StoryViewHolder(view)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is StoryViewHolder) {
            val tv_title=holder.tv_list



            tv_title.text=item.caption
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class StoryViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tv_list = view.findViewById<TextView>(R.id.tv_list)


    }

}

