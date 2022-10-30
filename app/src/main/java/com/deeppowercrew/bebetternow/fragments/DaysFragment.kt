package com.deeppowercrew.bebetternow.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.deeppowercrew.bebetternow.R
import com.deeppowercrew.bebetternow.adapters.DayModel
import com.deeppowercrew.bebetternow.adapters.DaysAdapter
import com.deeppowercrew.bebetternow.adapters.ExerciseModel
import com.deeppowercrew.bebetternow.databinding.DaysFragmentBinding
import com.deeppowercrew.bebetternow.utils.DialogManager
import com.deeppowercrew.bebetternow.utils.FragmentManager
import com.deeppowercrew.bebetternow.utils.MainViewModel


/**
 * description fragment
 */
class DaysFragment : Fragment(), DaysAdapter.Listener {

    private lateinit var binding: DaysFragmentBinding
    private var actionBarText: ActionBar? = null
    private lateinit var adapter: DaysAdapter
    private val model: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DaysFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.reset) {
            DialogManager.showDialog(activity as AppCompatActivity, R.string.reset_days_message,
                object : DialogManager.Listener {
                    override fun onClick() {
                        model.prefs?.edit()?.clear()?.apply()
                        adapter.submitList(fillDaysArray())
                    }
                })

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.currentDay = 0
        actionBarText = (activity as AppCompatActivity).supportActionBar
        actionBarText?.title = getString(R.string.app_name)
        initRecyclerView()

    }

    private fun initRecyclerView() = with(binding) {
        adapter = DaysAdapter(this@DaysFragment)
        recyclerViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        recyclerViewDays.adapter = adapter
        adapter.submitList(fillDaysArray())
    }

    private fun fillDaysArray(): ArrayList<DayModel> {
        val tempArray = ArrayList<DayModel>()
        var successfulDays = 0
        resources.getStringArray(R.array.day_exercise).forEach {
            model.currentDay++
            val exCounter = it.split(",").size
            tempArray.add(DayModel(it, 0, model.getExerciseCount() == exCounter))
        }

        binding.progressBar.max = tempArray.size

        tempArray.forEach {
            if (it.isDone) successfulDays++
        }
        updateSuccessfulDaysUI(tempArray.size - successfulDays, tempArray.size)
        return tempArray
    }

    private fun updateSuccessfulDaysUI(sucDays: Int, days: Int) = with(binding) {
        val rDays = getString(R.string.finish) + " $sucDays " + getString(R.string.days_left)
        fragmentDaysStatusText.text = rDays
        progressBar.progress = days - sucDays
    }

    private fun fillExerciseList(day: DayModel) {
        val tempList = ArrayList<ExerciseModel>()
        day.exercises.split(",").forEach {
            val exerciseList = resources.getStringArray(R.array.exercise)
            val exercise = exerciseList[it.toInt()]
            val exerciseArray = exercise.split("|")
            tempList.add(ExerciseModel(exerciseArray[0], exerciseArray[1], false, exerciseArray[2]))
        }
        model.mutableListExercise.value = tempList
//        model.mutableListExercise.observe(viewLifecycleOwner, {
//
//        })
    }

    companion object {


        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    private fun openThisDay(day: DayModel) {
        fillExerciseList(day)
        model.currentDay = day.dayNumber
        FragmentManager.setFragment(
            ExercisesListFragment.newInstance(),
            activity as AppCompatActivity
        )
    }

    override fun onClickDay(day: DayModel) {
        if (!day.isDone) {
            openThisDay(day)
        } else {
            DialogManager.showDialog(activity as AppCompatActivity, R.string.reset_day_message,
                object : DialogManager.Listener {
                    override fun onClick() {
                        model.savePrefs(day.dayNumber.toString(), 0)
                        openThisDay(day)
                    }
                })
        }
    }
}