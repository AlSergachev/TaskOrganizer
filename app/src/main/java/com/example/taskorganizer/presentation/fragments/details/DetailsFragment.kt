package com.example.taskorganizer.presentation.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.DetailsFragmentBinding
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.presentation.Constants
import com.example.taskorganizer.app.APP
import com.example.taskorganizer.app.App
import javax.inject.Inject

@Suppress("DEPRECATION")
class DetailsFragment : Fragment() {

    @Inject
    lateinit var detailsFactory: DetailsViewModelFactory

    private val NAME_FRAGMENT: String = "Show Task Details"
    private lateinit var task: TaskModel
    private lateinit var binding: DetailsFragmentBinding
    private lateinit var viewModel: DetailsViewModel

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
        taskDeadline.setText(task.deadline)
        checkBoxReminder.isChecked = task.isReminder
        taskPlace.setText(task.place)
        checkBoxDone.isChecked = task.isDone
        taskExcuse.text = task.excuse
    }

    private fun setListener() {
        binding.btnDelete.setOnClickListener {
            if (deleteTask()) {
                APP.toListFragment()
            } else {
                Toast.makeText(this.context, "Удаление не удалось", Toast.LENGTH_LONG).show()
            }
        }
        binding.btnEdit.setOnClickListener { editTask() }
        binding.btnSave.setOnClickListener {
            if (saveTask()) {
                Toast.makeText(context, "Сохранение выполнено успешно", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Сохранение не удалось", Toast.LENGTH_LONG).show()
                editTask()
            }
        }
    }

    private fun initialization() {
        setActivityParam()
        renderState()
        viewModel = ViewModelProvider(this, detailsFactory)[DetailsViewModel::class.java]
    }

    private fun setActivityParam() {
        APP.binding.btnCreateTask.isClickable = true
        APP.binding.btnListTask.isClickable = true
        APP.binding.btnDetailsTask.isClickable = false
        APP.binding.btnDetailsTask.setBackgroundColor(resources.getColor(R.color.selected_green))
        APP.binding.title.text = NAME_FRAGMENT

    }

    private fun editTask() {
        setClickableEditText(binding.taskTitle, true)
        setClickableEditText(binding.taskDescription, true)
        setClickableEditText(binding.taskDeadline, true)
        setClickableEditText(binding.taskPlace, true)
        setClickableEditText(binding.taskTitle, true)
        setClickableCheckBox(binding.checkBoxReminder, true)
        setClickableCheckBox(binding.checkBoxDone, true)
        binding.btnSave.visibility = View.VISIBLE
    }


    private fun setClickableEditText(v: EditText, bool: Boolean) = with(v) {
        isClickable = bool
        isLongClickable = bool
        isCursorVisible = bool
        isFocusable = bool
        isFocusableInTouchMode = bool
        requestFocus()
    }

    private fun setClickableCheckBox(v: CheckBox, bool: Boolean) = with(v) {
        isClickable = bool
        isLongClickable = bool
        isFocusable = bool
    }

    private fun saveTask(): Boolean {

        val newTask = TaskModel(
            title = binding.taskTitle.text.toString(),
            description = binding.taskDescription.text.toString(),
            deadline = binding.taskDeadline.text.toString(),     // todo: toTime
            isReminder = binding.checkBoxReminder.isChecked,
            place = binding.taskPlace.text.toString(),
            isDone = binding.checkBoxDone.isChecked
        )

        setClickableEditText(binding.taskTitle, false)
        setClickableEditText(binding.taskDescription, false)
        setClickableEditText(binding.taskDeadline, false)
        setClickableEditText(binding.taskPlace, false)
        setClickableEditText(binding.taskTitle, false)
        setClickableCheckBox(binding.checkBoxReminder, false)
        setClickableCheckBox(binding.checkBoxDone, false)
        binding.btnSave.visibility = View.GONE

        return (viewModel.delete(task) && viewModel.save(newTask))
    }

    private fun deleteTask(): Boolean {
        return viewModel.delete(task)
    }

}
