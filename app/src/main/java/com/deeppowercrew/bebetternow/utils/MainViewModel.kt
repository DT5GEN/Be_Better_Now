package com.deeppowercrew.bebetternow.utils

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deeppowercrew.bebetternow.adapters.ExerciseModel

class MainViewModel : ViewModel () {
    val mutableListExercise = MutableLiveData<ArrayList<ExerciseModel>>()
    var prefs: SharedPreferences? = null
    var currentDay = 0

    fun savePrefs(key: String, value: Int){
        prefs?.edit()?.putInt(key, value)?.apply()
    }

   fun getExerciseCount() : Int{
       return prefs?.getInt(currentDay.toString(), 0) ?: 0
   }
}