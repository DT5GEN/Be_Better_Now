package com.deeppowercrew.bebetternow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.deeppowercrew.bebetternow.fragments.DaysFragment
import com.deeppowercrew.bebetternow.utils.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }

    override fun onBackPressed() {
        if (FragmentManager.currentFragment is DaysFragment) super.onBackPressed()
        else FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }
}