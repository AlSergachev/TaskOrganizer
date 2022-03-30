package com.example.taskorganizer.presentation.fragments.details

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.DetailsFragmentBinding
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.presentation.utils.Constants
import com.example.taskorganizer.app.APP
import com.example.taskorganizer.app.App
import com.example.taskorganizer.presentation.utils.Notify
import com.example.taskorganizer.presentation.utils.Notify.*
import com.example.taskorganizer.presentation.utils.Priority
import java.util.*
import javax.inject.Inject

@Suppress("DEPRECATION")
class DetailsFragment : Fragment() {

    @Inject
    lateinit var detailsFactory: DetailsViewModelFactory
    private lateinit var task: TaskModel
    private lateinit var binding: DetailsFragmentBinding
    private lateinit var viewModel: DetailsViewModel
    private var deadlineLong: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity?.applicationContext as App).appComponent.injectDetailsFragment(this)
        task = arguments?.getParcelable(Constants.KEY_TASK) ?: TaskModel()
        initialization()
        setListener()
    }

    private fun renderState() = with(binding) {
        taskTitle.setText(task.title)
        taskDescription.setText(task.description)
        deadlineLong = task.deadline
        taskDeadline.text =
            DateFormat.format(Constants.DeadlineFormat, deadlineLong).toString()
        checkBoxReminder.isChecked = task.isReminder
        taskPlace.setText(task.place)
        checkBoxDone.isChecked = task.isDone
        radioGroup.clearCheck()
        created.text = task.createdDateFormatted
        getPriority(task)
        if (task.excuse == "null" || task.excuse == "") {
            taskExcuse.visibility = View.GONE
        } else {
            taskExcuse.text = task.excuse
        }
    }


    private fun setListener() {
        binding.btnEdit.setOnClickListener { editTask() }
        binding.btnDelete.setOnClickListener {
            val value = deleteTask()
            APP.toast(value)
            if (value == SUCCESS_DELETE) {
                APP.toListFragment()
            }
        }
        binding.btnSave.setOnClickListener {
            val value = saveTask()
            APP.toast(value)
            if (value == SUCCESS_SAVE) {
                APP.toListFragment()
            } else {
                editTask()
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

    private fun initialization() {
        viewModel = ViewModelProvider(this, detailsFactory)[DetailsViewModel::class.java]
        renderState()
    }

    private fun editTask() {
        enableClickEditText(binding.taskTitle)
        enableClickEditText(binding.taskDescription)
        enableClickEditText(binding.taskDeadline)
        enableClickEditText(binding.taskPlace)
        enableClickEditText(binding.taskTitle)
        enableClickCheckBox(binding.checkBoxReminder)
        enableClickCheckBox(binding.checkBoxDone)

        enableClickCheckBox(binding.highPriority)
        enableClickCheckBox(binding.normalPriority)
        enableClickCheckBox(binding.lowPriority)

        binding.btnSave.visibility = View.VISIBLE
        binding.btnDelete.visibility = View.GONE
        binding.btnEdit.visibility = View.GONE
    }

    private fun enableClickEditText(v: TextView) = with(v) {
        isClickable = true
        isLongClickable = true
        isCursorVisible = true
        isFocusable = true
        isFocusableInTouchMode = true
        requestFocus()
    }

    private fun enableClickCheckBox(v: Button) = with(v) {
        isClickable = true
        isLongClickable = true
        isFocusable = true
    }

    private fun setPriority() = when (binding.radioGroup.checkedRadioButtonId) {
        R.id.high_priority -> Priority.HIGH.ordinal
        R.id.normal_priority -> Priority.NORMAL.ordinal
        else -> Priority.LOW.ordinal
    }

    private fun getPriority(task: TaskModel) = when (task.priority) {
        Priority.HIGH.ordinal -> binding.highPriority.isChecked = true
        Priority.NORMAL.ordinal -> binding.normalPriority.isChecked = true
        else -> binding.lowPriority.isChecked = true
    }


    private fun saveTask(): Notify {

        val updateTask = TaskModel(
            id = task.id,
            title = binding.taskTitle.text.toString(),
            description = binding.taskDescription.text.toString(),
            deadline = deadlineLong,
            isReminder = binding.checkBoxReminder.isChecked,
            place = binding.taskPlace.text.toString(),
            isDone = binding.checkBoxDone.isChecked,
            priority = setPriority()
        )

        if (updateTask == task) return EQUAL_TASKS
        if (!viewModel.save(updateTask)) return ERROR_SAVE
        return SUCCESS_SAVE
    }

    private fun deleteTask(): Notify {
        return if (!viewModel.delete(task)) ERROR_DELETE
        else SUCCESS_DELETE
    }
}
