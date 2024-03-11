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
import com.route.news_application.models.Articles
import com.route.news_application.databinding.NewsItemBinding
import okhttp3.internal.notify

class NewsAdapter(var newsList : List<Articles?>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    fun updateList(newList:List<Articles?>){
        newsList = newList
        notifyDataSetChanged()
    }
    class NewsViewHolder(val binding : NewsItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Articles?) {
            binding.titleArticleTxt.text = data?.title
            binding.articleTxt.text = data?.author
            Glide.with(itemView)
                .load(data?.urlToImage)
                .placeholder(R.drawable.background) //until loading
                .error(R.drawable.route) // if any error in loading
                .override(Target.SIZE_ORIGINAL) //  Specify image dimensions if necessary
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        Log.e("Glide","Error Loading image",e)
                        return false
                    }
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        return false
                    }
                }) .into(binding.newsImage)




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