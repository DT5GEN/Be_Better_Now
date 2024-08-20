package com.deeppowercrew.bebetternow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.deeppowercrew.bebetternow.R
import com.deeppowercrew.bebetternow.adapters.ExercisesAdapter
import com.deeppowercrew.bebetternow.databinding.ExercisesListFragmentBinding
import com.deeppowercrew.bebetternow.utils.FragmentManager
import com.deeppowercrew.bebetternow.utils.MainViewModel


class ExercisesListFragment : Fragment() {

    private lateinit var binding: ExercisesListFragmentBinding
    private lateinit var adapter: ExercisesAdapter
    private var actionBarText: ActionBar? = null
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExercisesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBarText = (activity as AppCompatActivity).supportActionBar
        actionBarText?.title = getString(R.string.exercises_list)
        initExercisesList()
        model.mutableListExercise.observe(viewLifecycleOwner) {
            for (i in 0 until model.getExerciseCount()){
                it[i] = it[i].copy(isDone = true)
            }
            adapter.submitList(it)
        }

    }

    private fun initExercisesList() = with(binding) {
        adapter = ExercisesAdapter()
        exercisesListFragmentRecyclerView.layoutManager = LinearLayoutManager(activity)
        exercisesListFragmentRecyclerView.adapter = adapter
        buttonStart.setOnClickListener {
            FragmentManager.setFragment(PreparatoryFragment.newInstance(), activity as AppCompatActivity)
        }

    }



    companion object {

        @JvmStatic
        fun newInstance() = ExercisesListFragment()
    }
}