package com.route.news_application.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.news_application.R
import com.route.news_application.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    lateinit var categoryFragment : CategoriesFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onCategoryFragment()
    }

    private fun onCategoryFragment(){
        replaceFragment(CategoriesFragment {
            binding.titleTxt.text = it.text
            replaceFragment(NewsFragment(it.text))
        })

    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_conrainer,fragment,"")
            .addToBackStack("")
            .commit()
    }

}