package com.example.taskorganizer.presentation.fragments.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskorganizer.R
import com.example.taskorganizer.app.APP
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.presentation.Constants

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var listTask = emptyList<TaskModel>()

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: AppCompatTextView = view.findViewById(R.id.item_task_title)
        val deadline: AppCompatTextView = view.findViewById(R.id.item_task_deadline)
        val isDone: AppCompatCheckBox = view.findViewById(R.id.item_check_box_done)
        val isReminder: AppCompatImageButton = view.findViewById(R.id.item_reminder)
        val fog: View = view.findViewById(R.id.item_fog)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<TaskModel>) {
        listTask = list
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.title.text = listTask[position].title
        holder.deadline.text = listTask[position].deadline
        holder.isReminder.visibility = if (listTask[position].isReminder) {
            View.VISIBLE
        } else {
            View.GONE
        }
        holder.isDone.isChecked = listTask[position].isDone
        holder.fog.visibility = if (listTask[position].isDone) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }


    override fun getItemCount(): Int {
        return listTask.size
    }

    override fun onViewAttachedToWindow(holder: ListViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            clickTask(listTask[holder.adapterPosition])
        }
    }

    fun clickTask(task: TaskModel) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.KEY_TASK, task)
        APP.toDetailsFragment(bundle)
    }

//    override fun onViewDetachedFromWindow(holder: ListViewHolder) {
//        holder.itemView.setOnClickListener(null)
//    }
}