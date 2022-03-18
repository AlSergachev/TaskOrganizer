package com.example.taskorganizer.presentation.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.MainFragmentBinding
import com.example.taskorganizer.presentation.APP

class MainFragment : Fragment() {

    //    companion object {
//        fun newInstance() = MainFragment()
//    }
//
//    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }


    override fun onStart() {
        super.onStart()
        APP.binding.btnListTask.setBackgroundColor(resources.getColor(R.color.selected_green))
    }


    private fun setListener() {
        binding.btnCreate.setOnClickListener {
            APP.toCreateFragment()
        }
    }


//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}