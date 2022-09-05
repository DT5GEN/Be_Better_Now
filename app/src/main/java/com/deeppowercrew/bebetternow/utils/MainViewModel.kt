package com.deeppowercrew.bebetternow.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deeppowercrew.bebetternow.adapters.ExerciseModel

class MainViewModel : ViewModel () {
    val mutableListExercise = MutableLiveData<ArrayList<ExerciseModel>>()

}