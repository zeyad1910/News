package com.route.news_application.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.news_application.Constants
import com.route.news_application.R
import com.route.news_application.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    lateinit var categoryFragment : CategoriesFragment
    var settingsFragment = SettingsFragment()
    lateinit var actionBarDrawerToggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onCategoryFragment()
        navigationView()
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
    private fun navigationView(){
         actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.drawerLayout,
            R.string.nav_open,R.string.nav_close)
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        binding.imageBtnNavSideMenu.setOnClickListener {
            binding.drawerLayout.open()
        }
        binding.navigationView.setNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.cat_icon ->{
                    onCategoryFragment()
                    binding.titleTxt.text = Constants.appName
                    binding.drawerLayout.close()
                }
                R.id.sett_icon ->{
                    replaceFragment(settingsFragment)
                    binding.titleTxt.text = Constants.SETTINGS
                    binding.drawerLayout.close()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

}