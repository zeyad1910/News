package com.route.news_application.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.news_application.api.models.Categories
import com.route.news_application.databinding.CategoryItemsBinding
import com.route.news_application.databinding.FragmentCategoriesBinding

class CategoryAdapter(private val categoriesList : List<Categories>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    class CategoryViewHolder(private val binding : CategoryItemsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Categories){
            binding.imageView.setImageResource(data.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemsBinding.inflate(LayoutInflater.from(parent.context)
            ,parent,false))
    }

    override fun getItemCount(): Int {
       return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val data : Categories = categoriesList[position]

    }
}