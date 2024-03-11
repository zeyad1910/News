package com.route.news_application

import com.route.news_application.models.Categories

object Constants {
    const val IMAGE_KEY="IMAGEKEY"
    const val AUTHOR_KEY = "AUTHORKEY"
    const val TITLE_KEY="TITLEKEY"
    const val PUBLISHAT_KEY="PUBLISHATKEY"
    const val DESCRIPTION_KEY = "DESCRIPTIONKEY"
    const val URL_KEY = "URLKEY"
    const val appName = "News App"
    const val SETTINGS = "Settings"
    val categoriesList : List<Categories> = listOf(
        Categories(R.drawable.sports,R.drawable.category_item_background_red,"sports"),
        Categories(R.drawable.politics,R.drawable.category_item_background_blue,"entertainment"),
        Categories(R.drawable.health,R.drawable.category_item_background_pink,"health"),
        Categories(R.drawable.bussines,R.drawable.category_item_background_bony,"business"),
        Categories(R.drawable.environment,R.drawable.category_item_background_light_blue,"environment"),
        Categories(R.drawable.science,R.drawable.category_item_background_yellow,"science")
    )
}