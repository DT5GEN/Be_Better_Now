package com.deeppowercrew.bebetternow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.deeppowercrew.bebetternow.databinding.FragmentProgramsBinding
import com.deeppowercrew.bebetternow.databinding.ProgramsFragmentBinding


class ProgramsFragment : Fragment() {
    private lateinit var binding: ProgramsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProgramsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = ProgramsFragment()
    }
}
