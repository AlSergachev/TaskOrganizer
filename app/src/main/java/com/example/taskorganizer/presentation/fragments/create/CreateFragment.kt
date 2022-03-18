package com.example.taskorganizer.presentation.fragments.create

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.CreateFragmentBinding

class CreateFragment : Fragment() {

//    companion object {
//        fun newInstance() = CreateFragment()
//    }
//
//    private lateinit var viewModel: CreateViewModel

    lateinit var binding: CreateFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateFragmentBinding.inflate(layoutInflater, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

    }


//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(CreateViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}