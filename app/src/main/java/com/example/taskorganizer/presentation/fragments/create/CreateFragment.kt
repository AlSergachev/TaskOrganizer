@file:Suppress("DEPRECATION")

package com.example.taskorganizer.presentation.fragments.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.databinding.CreateFragmentBinding
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.app.APP
import com.example.taskorganizer.app.App
import com.example.taskorganizer.presentation.utils.Notify
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
        APP.binding.title.text = NAME_FRAGMENT
        setListener()
    }

    private fun setListener() {
        binding.btnSave.setOnClickListener {
            val value = saveTask()
            APP.toast(value)
            if (value == Notify.SUCCESS_SAVE) {
                APP.toListFragment()
            }
        }
    }

    private fun saveTask(): Notify {
        val task = TaskModel(
            title = binding.taskTitle.text.toString(),
            description = binding.taskDescription.text.toString(),
            deadline = binding.taskDeadline.text.toString(),     // todo: toTime
            isReminder = binding.checkBoxReminder.isChecked,
            place = binding.taskPlace.text.toString()
        )
        if (viewModel.isEmpty(task)) {
            return Notify.EMPTY_TASK
        }
        return if (viewModel.save(task)) {
            Notify.SUCCESS_SAVE
        } else {
            Notify.ERROR_SAVE
        }
    }


}