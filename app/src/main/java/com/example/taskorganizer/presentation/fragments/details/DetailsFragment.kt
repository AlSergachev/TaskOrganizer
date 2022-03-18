package com.example.taskorganizer.presentation.fragments.details

import android.os.Binder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.DetailsFragmentBinding
import com.example.taskorganizer.presentation.APP

@Suppress("DEPRECATION")
class DetailsFragment : Fragment() {

//    companion object {
//        fun newInstance() = DetailsFragment()
//    }
//
//    private lateinit var viewModel: DetailsViewModel

    private lateinit var binding: DetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()

    }

    private fun setListener() {
        binding.btnDelete.setOnClickListener {
            APP.toListFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        APP.binding.btnDetailsTask.setBackgroundColor(resources.getColor(R.color.selected_green))
    }



//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}