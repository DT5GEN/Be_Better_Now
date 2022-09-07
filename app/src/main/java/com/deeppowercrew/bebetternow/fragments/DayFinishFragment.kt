package com.deeppowercrew.bebetternow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.deeppowercrew.bebetternow.R
import com.deeppowercrew.bebetternow.databinding.DayFinishLayoutBinding
import com.deeppowercrew.bebetternow.utils.FragmentManager
import pl.droidsonroids.gif.GifDrawable


/**
 * description fragment
 */

class DayFinishFragment : Fragment() {

    private lateinit var binding: DayFinishLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DayFinishLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView4.setImageResource(R.drawable.splscr)
        binding.buttonDone.setOnClickListener {
            FragmentManager.setFragment(DaysFragment.newInstance(), activity as AppCompatActivity)
        }
    }


    companion object {
        /**
        fdgd
         */

        @JvmStatic
        fun newInstance() = DayFinishFragment()
    }
}