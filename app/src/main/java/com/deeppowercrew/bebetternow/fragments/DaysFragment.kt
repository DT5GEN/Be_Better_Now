package com.deeppowercrew.bebetternow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.deeppowercrew.bebetternow.R
import com.deeppowercrew.bebetternow.adapters.DayModel
import com.deeppowercrew.bebetternow.adapters.DaysAdapter
import com.deeppowercrew.bebetternow.adapters.ExerciseModel
import com.deeppowercrew.bebetternow.databinding.FragmentDaysBinding
import com.deeppowercrew.bebetternow.utils.FragmentManager
import com.deeppowercrew.bebetternow.utils.MainViewModel


/**
 * description fragment
 */
class DaysFragment : Fragment(), DaysAdapter.Listener {

    private lateinit var binding: FragmentDaysBinding
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

    }

    private fun initRecyclerView() = with(binding) {
        val adapter = DaysAdapter(this@DaysFragment)
        recyclerViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        recyclerViewDays.adapter = adapter
        adapter.submitList(fillDaysArray())
    }

    private fun fillDaysArray(): ArrayList<DayModel> {
        val tempArray = ArrayList<DayModel>()
        resources.getStringArray(R.array.day_exercise).forEach {
            tempArray.add(DayModel(it, false))
        }
        return tempArray
    }

    private fun fillExerciseList(day: DayModel) {
        val tempList = ArrayList<ExerciseModel>()
        day.exercises.split(",").forEach {
            val exerciseList = resources.getStringArray(R.array.exercise)
            val exercise = exerciseList[it.toInt()]
            val exerciseArray = exercise.split("|")
            tempList.add(ExerciseModel(exerciseArray[0],exerciseArray[1],exerciseArray[2]))
        }
        model.mutableListExercise.value = tempList
//        model.mutableListExercise.observe(viewLifecycleOwner, {
//
//        })
    }

    companion object {
        /**
        fdgd
         */

        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    override fun onClickDay(day: DayModel) {
        fillExerciseList(day)
        FragmentManager.setFragment(
            ExercisesListFragment.newInstance(),
            activity as AppCompatActivity
        )
    }
}