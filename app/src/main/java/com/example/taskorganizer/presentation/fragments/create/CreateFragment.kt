@file:Suppress("DEPRECATION")

package com.example.taskorganizer.presentation.fragments.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.CreateFragmentBinding
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase
import com.example.taskorganizer.app.APP

class CreateFragment : Fragment() {

    @Suppress("PrivatePropertyName")
    private val NAME_FRAGMENT: String = "Create New Task"
    private val saveTaskUseCase = SaveTaskUseCase()

    //    companion object {
//        fun newInstance() = CreateFragment()
//    }
//
    private lateinit var binding: CreateFragmentBinding
    private lateinit var viewModel: CreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CreateFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
        setListener()
    }


    private fun initialization() {
        setActivityParam()
        viewModel = ViewModelProvider(this)[CreateViewModel::class.java]

    }

    private fun setListener() {
        binding.btnSave.setOnClickListener {
            if (saveTask()) {
                APP.toListFragment()
            } else {
                Toast.makeText(context, "Сохранение не удалось", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun saveTask(): Boolean {
        val task = TaskModel(
            title = binding.taskTitle.toString(),
            description = binding.taskDescription.toString(),
            deadline = binding.taskDeadline.toString(),     // todo: toTime
            isReminder = binding.checkBoxReminder.isChecked,
            place = binding.taskPlace.toString()
        )
        return saveTaskUseCase.execute(task)
    }

    private fun setActivityParam() {
        APP.binding.btnDetailsTask.isClickable = true
        APP.binding.btnListTask.isClickable = true
        APP.binding.btnCreateTask.isClickable = false
        APP.binding.btnCreateTask.setBackgroundColor(resources.getColor(R.color.selected_green))
        APP.binding.title.text = NAME_FRAGMENT
    }
}