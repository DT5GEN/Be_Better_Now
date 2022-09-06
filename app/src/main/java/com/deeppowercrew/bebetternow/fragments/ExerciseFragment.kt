package com.deeppowercrew.bebetternow.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.deeppowercrew.bebetternow.R
import com.deeppowercrew.bebetternow.adapters.ExerciseModel
import com.deeppowercrew.bebetternow.databinding.ExerciseFragmentBinding
import com.deeppowercrew.bebetternow.utils.MainViewModel
import com.deeppowercrew.bebetternow.utils.TimeUtils
import pl.droidsonroids.gif.GifDrawable


/**
 * description fragment
 */
class ExerciseFragment : Fragment() {

    private var timer: CountDownTimer? = null
    private lateinit var binding: ExerciseFragmentBinding
    private var exerciseCounter = 0
    private var exerciseList: ArrayList<ExerciseModel>? = null
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExerciseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.mutableListExercise.observe(viewLifecycleOwner) {
            exerciseList = it
            nextExercise()
        }
        binding.buttonNext.setOnClickListener {
            nextExercise()
        }

    }


    private fun nextExercise() {
        if (exerciseCounter < exerciseList?.size!!) {
            val ex = exerciseList?.get(exerciseCounter++) ?: return
            showExercise(ex)
            setExerciseType(ex)
            showNextExercise()
        } else {
            Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show()
        }


    }

    private fun showExercise(exercise: ExerciseModel) = with(binding) {
        headGif.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
        exerciseName.text = exercise.name

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
            val name = ex.name + " : ${ex.name}"
            textNextExercise.text = name
        } else {
            nextGif.setImageDrawable(GifDrawable(root.context.assets, "rest.gif"))
            textNextExercise.text = getString(R.string.well_done)
        }
    }

    private fun getTimeType(exercise: ExerciseModel){

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
        timer?.cancel()
    }


    companion object {
        /**
        fdgd
         */

        @JvmStatic
        fun newInstance() = ExerciseFragment()
    }
}