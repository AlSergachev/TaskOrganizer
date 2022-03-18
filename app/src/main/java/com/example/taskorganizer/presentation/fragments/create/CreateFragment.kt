@file:Suppress("DEPRECATION")

package com.example.taskorganizer.presentation.fragments.create

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.CreateFragmentBinding
import com.example.taskorganizer.presentation.APP

class CreateFragment : Fragment() {

//    companion object {
//        fun newInstance() = CreateFragment()
//    }
//
//    private lateinit var viewModel: CreateViewModel

    private lateinit var binding: CreateFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setListener()
    }

    private fun init() {

    }

    override fun onStart() {
        super.onStart()
        APP.binding.btnCreateTask.setBackgroundColor(resources.getColor(R.color.selected_green))
    }

    private fun setListener() {
        binding.btnSave.setOnClickListener {
            APP.toListFragment()
        }
    }


//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(CreateViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}