@file:Suppress("DEPRECATION")

package com.example.taskorganizer.presentation.fragments.create

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.CreateFragmentBinding
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.app.APP
import com.example.taskorganizer.app.App
import com.example.taskorganizer.presentation.Constants
import javax.inject.Inject

class CreateFragment : Fragment() {

    @Suppress("PrivatePropertyName")
    private val NAME_FRAGMENT: String = "Create New Task"

    @Inject
    lateinit var createFactory: CreateViewModelFactory

    private lateinit var binding: CreateFragmentBinding
    private lateinit var viewModel: CreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CreateFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity?.applicationContext as App).appComponent.injectCreateFragment(this)
        viewModel = ViewModelProvider(this, createFactory)[CreateViewModel::class.java]
        setActivityParam()
        setListener()
    }

    private fun setListener() {
        binding.btnSave.setOnClickListener {
            if (saveTask()) {
                Toast.makeText(context, "Сохранение удалось", Toast.LENGTH_LONG).show()
                APP.toListFragment()
            } else {
                Toast.makeText(context, "Сохранение не удалось", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun saveTask(): Boolean {
        Log.e(Constants.TAG, "CreateFragment.saveTask")
        val task = TaskModel(
            title = binding.taskTitle.text.toString(),
            description = binding.taskDescription.text.toString(),
            deadline = binding.taskDeadline.text.toString(),     // todo: toTime
            isReminder = binding.checkBoxReminder.isChecked,
            place = binding.taskPlace.text.toString()
        )
        Log.e(Constants.TAG, "CreateFragment.saveTask")

        try {
            viewModel.save(task)
        } catch (e: Exception) {
            return false
        }
        return true
    }

    private fun setActivityParam() {
        APP.binding.btnDetailsTask.isClickable = true
        APP.binding.btnListTask.isClickable = true
        APP.binding.btnCreateTask.isClickable = false
        APP.binding.btnCreateTask.setBackgroundColor(resources.getColor(R.color.selected_green))
        APP.binding.title.text = NAME_FRAGMENT
    }
}