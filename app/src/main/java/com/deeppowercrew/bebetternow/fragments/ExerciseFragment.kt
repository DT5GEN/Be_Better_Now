package com.deeppowercrew.bebetternow.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.deeppowercrew.bebetternow.R
import com.deeppowercrew.bebetternow.adapters.ExerciseModel
import com.deeppowercrew.bebetternow.databinding.ExerciseFragmentBinding
import com.deeppowercrew.bebetternow.utils.FragmentManager
import com.deeppowercrew.bebetternow.utils.MainViewModel
import com.deeppowercrew.bebetternow.utils.TimeUtils
import pl.droidsonroids.gif.GifDrawable


class ExerciseFragment : Fragment() {

    private var timer: CountDownTimer? = null
    private lateinit var binding: ExerciseFragmentBinding
    private var exerciseCounter = 0
    private var currentDay = 0
    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var actionBarText: ActionBar? = null
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExerciseFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentDay = model.currentDay
        exerciseCounter = model.getExerciseCount()

        Log.d(
            "MyLog",
            "onViewCreated() called with: Counter ${model.getExerciseCount()}"
        )

        actionBarText = (activity as AppCompatActivity).supportActionBar
            model.mutableListExercise.observe(viewLifecycleOwner) {
            exerciseList = it
            nextExercise()
        }
        binding.buttonNext.setOnClickListener {
            timer?.cancel()

            nextExercise()

        }

    }


    private fun nextExercise() {
        binding.progressBar2.max = 0
        if (exerciseCounter < exerciseList?.size!!) {
            val ex = exerciseList?.get(exerciseCounter++) ?: return

            showExercise(ex)
            setExerciseType(ex)
            showNextExercise()
        } else {
            exerciseCounter++
            FragmentManager.setFragment(DayFinishFragment.newInstance(), activity as AppCompatActivity)
        }


    }

    private fun showExercise(exercise: ExerciseModel) = with(binding) {
        headGif.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
        exerciseName.text = exercise.name
        val title = "$exerciseCounter / ${exerciseList?.size}"
        actionBarText?.title = title

    }

    private fun setExerciseType(exercise: ExerciseModel) {
        if (exercise.time.startsWith("x")) {
            binding.textTimer.text = exercise.time
        } else {
            startTimer(exercise)
        }
    }


    private fun showNextExercise() = with(binding) {
        if (exerciseCounter < exerciseList?.size!!) {
            val ex = exerciseList?.get(exerciseCounter) ?: return
            nextGif.setImageDrawable(GifDrawable(root.context.assets, ex.image))
           // val name = ex.name + " : ${ex.time}"
            setTimeType(ex)
        } else {
            nextGif.setImageDrawable(GifDrawable(root.context.assets, "rest.gif"))
            textNextExercise.text = getString(R.string.well_done)

        }
    }

    private fun setTimeType(exercise: ExerciseModel){
        if (exercise.time.startsWith("x")) {
            binding.textNextExercise.text = exercise.time
        } else {
            val name = exercise.name + "  ${TimeUtils.getTime(exercise.time.toLong() * 1000)}"
            binding.textNextExercise.text = name
        }
    }


    private fun startTimer(exercise: ExerciseModel) = with(binding) {
        progressBar2.max = exercise.time.toInt() * 1000
        timer?.cancel()
        timer = object : CountDownTimer(exercise.time.toLong() * 1000, 50) {
            override fun onTick(restTime: Long) {
                textTimer.text = TimeUtils.getTime(restTime)
                progressBar2.progress = restTime.toInt()
            }

            override fun onFinish() {
                nextExercise()
            }

        }.start()
    }

    override fun onDetach() {
        super.onDetach()
        model.savePrefs(currentDay.toString(), exerciseCounter -1)
        timer?.cancel()
    }


    companion object {

        @JvmStatic
        fun newInstance() = ExerciseFragment()
    }
}