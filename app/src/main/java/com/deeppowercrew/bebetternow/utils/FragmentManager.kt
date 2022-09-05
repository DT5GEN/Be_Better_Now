package com.deeppowercrew.bebetternow.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.deeppowercrew.bebetternow.R

object FragmentManager {

    var currentFragment: Fragment? = null

    fun setFragment(newFragment: Fragment, activity: AppCompatActivity) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_place_holder, newFragment)
        transaction.commit()

        currentFragment = newFragment

    }
}