package com.route.news_application.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.route.news_application.Constants
import com.route.news_application.Constants.categoriesList
import com.route.news_application.R
import com.route.news_application.adapter.CategoryAdapter
import com.route.news_application.api.models.Categories
import com.route.news_application.databinding.FragmentCategoriesBinding


class CategoriesFragment(val onClickForNewsFragment : (category:Categories) -> Unit) : Fragment() {
    lateinit var binding : FragmentCategoriesBinding
    private lateinit var adapter : CategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRV()
        initListeners()
    }

    private fun prepareRV(){
        adapter= CategoryAdapter(categoriesList)
        binding.categoriesRv.adapter=adapter
        binding.categoriesRv.layoutManager = GridLayoutManager(requireContext(),2)
    }

    private fun initListeners(){
        adapter.categoryClickListener = object : CategoryAdapter.SetOnCategoryClickListener{
            override fun onCategoryClickListener(data: Categories, position: Int) {
                onClickForNewsFragment.invoke(data)
            }

        }
    }
}