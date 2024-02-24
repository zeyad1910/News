package com.route.news_application.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.route.news_application.Constants
import com.route.news_application.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putDataOnViews()
    }

    private fun putDataOnViews() {
        val intent = intent
        Glide.with(binding.root)
            .load(intent.getStringExtra(Constants.IMAGE_KEY))
            .into(binding.imageDetails)
        binding.newsTxt.text = intent.getStringExtra(Constants.AUTHOR_KEY)
        binding.newsTitleTxt.text = intent.getStringExtra(Constants.TITLE_KEY)
        binding.newsDescriptionTxt.text = intent.getStringExtra(Constants.DESCRIPTION_KEY)
        binding.forDateTxt.text = intent.getStringExtra(Constants.PUBLISHAT_KEY)
        binding.btnForLink.setOnClickListener {
            val url = intent.getStringExtra(Constants.URL_KEY)
            val intentForLink = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intentForLink)
        }
    }
}