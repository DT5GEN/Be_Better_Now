package com.deeppowercrew.bebetternow.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.deeppowercrew.bebetternow.R
import com.deeppowercrew.bebetternow.databinding.PreparatoryFragmentBinding
import com.deeppowercrew.bebetternow.utils.FragmentManager
import com.deeppowercrew.bebetternow.utils.TimeUtils

const val COUNT_DOWN_TIME = 3000L

class PreparatoryFragment : Fragment() {

    private lateinit var binding: PreparatoryFragmentBinding
    private lateinit var timer: CountDownTimer
    private var actionBarText: ActionBar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PreparatoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBarText = (activity as AppCompatActivity).supportActionBar
        actionBarText?.title = getString(R.string.app_name)
        binding.preparatoryFragmentProgressBar.max = COUNT_DOWN_TIME.toInt()
        startTimer()
    }

    private fun startTimer() = with(binding) {
        timer = object : CountDownTimer(COUNT_DOWN_TIME, 50) {
            override fun onTick(restTime: Long) {
                preparatoryFragmentTextTimer.text = TimeUtils.getTime(restTime)
                preparatoryFragmentProgressBar.progress = restTime.toInt()
            }

            override fun onFinish() {
                FragmentManager.setFragment(
                    ExerciseFragment.newInstance(),
                    activity as AppCompatActivity
                )
            }

        }.start()
    }

    override fun onDetach() {
        super.onDetach()
        timer.cancel()
    }

    companion object {
              @JvmStatic
        fun newInstance() = PreparatoryFragment()
    }
}