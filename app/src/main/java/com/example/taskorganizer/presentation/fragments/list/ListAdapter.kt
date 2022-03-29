package com.example.taskorganizer.presentation.fragments.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskorganizer.R
import com.example.taskorganizer.app.APP
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.presentation.utils.Constants
import com.example.taskorganizer.presentation.utils.Priority

@Suppress("DEPRECATION")
class ListAdapter(private val onUpdateCallback: (TaskModel, Int) -> Unit) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var listTask = emptyList<TaskModel>()

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: AppCompatTextView = view.findViewById(R.id.item_task_title)
        val deadline: AppCompatTextView = view.findViewById(R.id.item_task_deadline)
        val isDone: AppCompatCheckBox = view.findViewById(R.id.item_check_box_done)
        val isReminderView: FrameLayout = view.findViewById(R.id.item_reminder_view)
        val isReminder: AppCompatImageButton = view.findViewById(R.id.item_reminder)
        val priority: View = view.findViewById(R.id.priority)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<TaskModel>) {
        listTask = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ListViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.title.text = listTask[position].title
        holder.deadline.text =
            DateFormat.format(Constants.DeadlineFormat, listTask[position].deadline).toString()
        holder.isReminder.visibility = if (listTask[position].isReminder) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
        holder.isDone.isChecked = listTask[position].isDone
        holder.priority.setBackgroundColor(getPriority(listTask[position]))


    }

    override fun getItemCount() = listTask.size

    override fun onViewAttachedToWindow(holder: ListViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.isDone.setOnClickListener {
            onUpdateCallback(listTask[holder.adapterPosition], Constants.IS_DONE)
        }
        holder.isReminderView.setOnClickListener {
            onUpdateCallback(listTask[holder.adapterPosition], Constants.IS_REMINDER)
        }
        holder.isReminder.setOnClickListener {
            onUpdateCallback(listTask[holder.adapterPosition], Constants.IS_REMINDER)
        }
        holder.itemView.setOnClickListener {
            clickTask(listTask[holder.adapterPosition])
        }
    }

    private fun getPriority(task: TaskModel) = when (task.priority) {
        Priority.HIGH.ordinal -> APP.resources.getColor(R.color.high_priority)
        Priority.NORMAL.ordinal -> APP.resources.getColor(R.color.normal_priority)
        else -> APP.resources.getColor(R.color.low_priority)
    }

    private fun clickTask(task: TaskModel) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.KEY_TASK, task)
        APP.navController.navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
    }

}