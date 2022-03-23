@file:Suppress("DEPRECATION")

package com.example.taskorganizer.presentation.fragments.create

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
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
import java.util.*
import javax.inject.Inject

class CreateFragment : Fragment() {

    @Inject
    lateinit var createFactory: CreateViewModelFactory
    private lateinit var binding: CreateFragmentBinding
    private lateinit var viewModel: CreateViewModel
    private lateinit var calendar: Calendar
    private var mYear = 0
    private var mMonth = 0
    private var mDay = 0
    private var mHour = 0
    private var mMinute = 0

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
            setDeadline()
        }
    }

    @Suppress("RedundantSamConstructor")
    private fun setDeadline() {
        calendar = Calendar.getInstance()
        getCurrentDateAndTime()
        DatePickerDialog(
            APP,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                TimePickerDialog(
                    APP,
                    TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                        mHour = hourOfDay
                        mMinute = minute
                        calendar.set(mYear, mMonth, mDay, mHour, mMinute)
                        binding.taskDeadline.text =
                            DateFormat.format("EEE, d MMM yyyy HH:mm", calendar).toString()
                    }, mHour, mMinute, true
                ).show()
                mYear = year
                mMonth = month
                mDay = dayOfMonth
            }, mYear, mMonth, mDay
        ).show()
    }

    private fun getCurrentDateAndTime() {
        mYear = calendar.get(Calendar.YEAR)
        mMonth = calendar.get(Calendar.MONTH)
        mDay = calendar.get(Calendar.DAY_OF_MONTH)
        mHour = calendar.get(Calendar.HOUR)
        mMinute = calendar.get(Calendar.MINUTE)
    }

    private fun saveTask(): Notify {

        val task = TaskModel(
            title = binding.taskTitle.text.toString(),
            description = binding.taskDescription.text.toString(),
            deadline = binding.taskDeadline.text.toString(),
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

    private fun startProgressBar(){
        binding.scrollView.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
        binding.scrollView.visibility = View.INVISIBLE
    }

}