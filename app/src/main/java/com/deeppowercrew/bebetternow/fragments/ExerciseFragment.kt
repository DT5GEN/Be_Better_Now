package com.deeppowercrew.bebetternow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.deeppowercrew.bebetternow.adapters.ExerciseModel
import com.deeppowercrew.bebetternow.databinding.ExerciseFragmentBinding
import com.deeppowercrew.bebetternow.utils.MainViewModel
import pl.droidsonroids.gif.GifDrawable


/**
 * description fragment
 */
class ExerciseFragment : Fragment() {

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
            val ex = exerciseList?.get(exerciseCounter++)
            showExercise(ex)
        } else {
            Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show()
        }


    }

    private fun showExercise(exercise: ExerciseModel?) = with(binding) {
        headGif.setImageDrawable(exercise?.let { GifDrawable(root.context.assets, it.image) })
        exerciseName.text = exercise?.name ?: return@with


    }


    companion object {
        /**
        fdgd
         */

        @JvmStatic
        fun newInstance() = ExerciseFragment()
    }
}