package org.android.go.sopt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.android.go.sopt.Gallery.GalleryFragment
import org.android.go.sopt.Home.HomeFragment
import org.android.go.sopt.Search.SearchFragment
import org.android.go.sopt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.cv_main)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.cv_main, HomeFragment.newInstance()).commit()
        }

        binding.bnvMain.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.menu_home -> {
                    changeFragment(HomeFragment())
                }
                R.id.menu_gallery->{
                    changeFragment(GalleryFragment())
                }
                R.id.menu_search -> {
                    changeFragment(SearchFragment())
                }
            }
            true
        }
    }
    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.cv_main,fragment)
            .commit()
    }
}
