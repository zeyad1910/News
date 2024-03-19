package com.route.news_application.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.route.news_application.R
import com.route.news_application.data.models.Articles
import com.route.news_application.databinding.NewsItemBinding

class NewsAdapter(var newsList : List<Articles?>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    fun updateList(newList:List<Articles?>){
        newsList = newList
        notifyDataSetChanged()
    }
    class NewsViewHolder(val binding : NewsItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Articles?) {
           binding.article = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val v = NewsItemBinding.inflate(LayoutInflater.from(parent.context))
        return NewsViewHolder(v)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val data : Articles? = newsList[position]
         holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemViewClickListener?.itemViewClickListener(data,position)
        }
    }
    var onItemViewClickListener : SetOnItemViewClickListener?=null
    interface SetOnItemViewClickListener{
        fun itemViewClickListener(data : Articles?, position: Int)
    }
}