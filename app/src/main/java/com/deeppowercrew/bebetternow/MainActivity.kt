package com.deeppowercrew.bebetternow

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.deeppowercrew.bebetternow.fragments.DaysFragment
import com.deeppowercrew.bebetternow.utils.FragmentManager
import com.deeppowercrew.bebetternow.utils.MainViewModel

class MainActivity : AppCompatActivity() {

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model.prefs = getSharedPreferences("main", MODE_PRIVATE)
        FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
        override fun onBackPressed() {
        if (FragmentManager.currentFragment is DaysFragment) super.onBackPressed()
        else FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }
}