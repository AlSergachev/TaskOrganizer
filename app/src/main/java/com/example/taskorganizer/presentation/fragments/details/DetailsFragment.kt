package com.example.taskorganizer.presentation.fragments.details

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.DetailsFragmentBinding
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.DeleteTaskUseCase
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase
import com.example.taskorganizer.presentation.Constants
import com.example.taskorganizer.presentation.app.APP

@Suppress("DEPRECATION")
class DetailsFragment : Fragment() {

    private final val NAME_FRAGMENT: String = "Show Task Details"
    private lateinit var task: TaskModel
    private val saveTaskUseCase = SaveTaskUseCase()
    private val deleteTaskUseCase = DeleteTaskUseCase()

//    companion object {
//        fun newInstance() = DetailsFragment()
//    }
//

    private lateinit var binding: DetailsFragmentBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(layoutInflater, container, false)
        task = arguments?.getParcelable(Constants.KEY_TASK) ?: TaskModel()
        return binding.root
    }

    private fun renderState() = with(binding){
        taskTitle.setText(task.title)
        taskDescription.setText(task.description)
        taskDeadline.setText(task.deadline)
        checkBoxReminder.isChecked = task.isReminder
        taskPlace.setText(task.place)
        checkBoxDone.isChecked = task.isDone
        taskExcuse.text = task.excuse
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
        setListener()

    }

    private fun setListener() {
        binding.btnDelete.setOnClickListener {
            if (deleteTask()) {
                APP.toListFragment()
            } else {
                Toast.makeText(this.context, "Удаление не удалось", Toast.LENGTH_LONG).show()

            }
        }
        binding.btnEdit.setOnClickListener {
            editTask()
        }
        binding.btnSave.setOnClickListener {
            if(saveTask()){
                Toast.makeText(context, "Сохранение выполнено успешно", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context, "Сохранение не удалось", Toast.LENGTH_LONG).show()
                editTask()
            }
        }
    }

    private fun initialization() {
        setActivityParam()
        renderState()
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

    }

    private fun setActivityParam() {
        APP.binding.btnDetailsTask.setBackgroundColor(resources.getColor(R.color.selected_green))
        APP.binding.title.text = NAME_FRAGMENT
    }

    private fun editTask(){
        setClickableEditText(binding.taskTitle, true)
        setClickableEditText(binding.taskDescription, true)
        setClickableEditText(binding.taskDeadline, true)
        setClickableEditText(binding.taskPlace, true)
        setClickableEditText(binding.taskTitle, true)
        setClickableCheckBox(binding.checkBoxReminder, true)
        setClickableCheckBox(binding.checkBoxDone, true)
        binding.btnSave.visibility = View.VISIBLE
    }


    private fun setClickableEditText(v: EditText, bool : Boolean) = with(v){
        isClickable =  bool
        isLongClickable = bool
        isCursorVisible = bool
        isFocusable = bool
        isFocusableInTouchMode = bool
        requestFocus()
    }

    private fun setClickableCheckBox(v: CheckBox, bool : Boolean) = with(v){
        isClickable =  bool
        isLongClickable = bool
        isFocusable = bool
    }




    private fun saveTask(): Boolean{

        val newTask = TaskModel(
            title = binding.taskTitle.toString(),
            description = binding.taskDescription.toString(),
            deadline = binding.taskDeadline.toString(),     // todo: toTime
            isReminder = binding.checkBoxReminder.isChecked,
            place = binding.taskPlace.toString(),
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

        deleteTaskUseCase.execute(task)
        return saveTaskUseCase.execute(newTask)
    }
    private fun deleteTask(): Boolean{
        return deleteTaskUseCase.execute(task)
    }

}
