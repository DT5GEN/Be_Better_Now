package com.deeppowercrew.bebetternow

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.deeppowercrew.bebetternow.fragments.DaysFragment
import com.deeppowercrew.bebetternow.fragments.ProgramsFragment
import com.deeppowercrew.bebetternow.utils.FragmentManager
import com.deeppowercrew.bebetternow.utils.MainViewModel

class MainActivity : AppCompatActivity() {

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model.prefs = getSharedPreferences("main", MODE_PRIVATE)
        FragmentManager.setFragment(ProgramsFragment.newInstance(), this)
    }

    override fun onBackPressed() {
        if (FragmentManager.currentFragment is ProgramsFragment) super.onBackPressed()
        else FragmentManager.setFragment(ProgramsFragment.newInstance(), this)
    }
}


/*
class MainActivity : AppCompatActivity() {

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model.prefs = getSharedPreferences("main", MODE_PRIVATE)
        FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }

    override fun onBackPressed() {
        if (FragmentManager.currentFragment is DaysFragment) super.onBackPressed()
        else FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }
}

*/