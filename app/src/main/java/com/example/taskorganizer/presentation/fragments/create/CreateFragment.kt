@file:Suppress("DEPRECATION")

package com.example.taskorganizer.presentation.fragments.create

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.CreateFragmentBinding
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.app.APP
import com.example.taskorganizer.app.App
import com.example.taskorganizer.presentation.utils.Constants
import com.example.taskorganizer.presentation.utils.Notify
import com.example.taskorganizer.presentation.utils.Priority
import java.util.*
import javax.inject.Inject

class CreateFragment : Fragment() {

    @Inject
    lateinit var createFactory: CreateViewModelFactory
    private lateinit var binding: CreateFragmentBinding
    private lateinit var viewModel: CreateViewModel
    private var deadlineLong: Long = 0

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
        setListener()
    }

    private fun setListener() {
        binding.btnSave.setOnClickListener {
            startProgressBar()
            val value = saveTask()
            stopProgressBar()
            APP.toast(value)
            if (value == Notify.SUCCESS_SAVE) {
                APP.toListFragment()
            }
        }
        binding.taskDeadline.setOnClickListener {
            viewModel.setDeadline(requireContext(), ::saveDeadline)
        }
    }

    private fun saveDeadline(calendar: Calendar) {
        deadlineLong = calendar.timeInMillis
        binding.taskDeadline.text =
            DateFormat.format(Constants.DeadlineFormat, deadlineLong).toString()
    }

    private fun saveTask(): Notify {

        val task = TaskModel(
            title = binding.taskTitle.text.toString(),
            description = binding.taskDescription.text.toString(),
            deadline = deadlineLong,
            isReminder = binding.checkBoxReminder.isChecked,
            place = binding.taskPlace.text.toString(),
            priority = setPriority()
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

    private fun setPriority() = when (binding.radioGroup.checkedRadioButtonId) {
        R.id.high_priority -> Priority.HIGH.ordinal
        R.id.normal_priority -> Priority.NORMAL.ordinal
        else -> Priority.LOW.ordinal
    }

    private fun startProgressBar() {
        binding.scrollView.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.scrollView.visibility = View.VISIBLE
    }

}