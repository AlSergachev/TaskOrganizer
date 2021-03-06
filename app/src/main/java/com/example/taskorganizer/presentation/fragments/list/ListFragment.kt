package com.example.taskorganizer.presentation.fragments.list

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.MainFragmentBinding
import com.example.taskorganizer.app.APP
import com.example.taskorganizer.app.App
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.presentation.utils.Constants
import com.example.taskorganizer.presentation.utils.SortType
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var listFactory: ListViewModelFactory
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity?.applicationContext as App).appComponent.injectListFragment(this)
        initialization()
        setListener()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initialization() {
        viewModel = ViewModelProvider(this, listFactory)[ListViewModel::class.java]
        adapter = ListAdapter(::updateTask)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
        viewModel.sortBy(APP.sortType)

        viewModel.getList().observe(viewLifecycleOwner) { newList ->
            updateList(newList)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateList(list: List<TaskModel>) {
        adapter.setList(list)
        adapter.notifyDataSetChanged()
    }

    private fun setListener() {
        binding.btnCreate.setOnClickListener {
            APP.toCreateFragment()
        }
        binding.btnSort.setOnClickListener {
            showSortDialog()
        }
        binding.btnUpdate.setOnClickListener {
            updateList(viewModel.getList().value!!)
        }
    }


    private fun showSortDialog() {
        val dialog = AlertDialog.Builder(requireContext())
            .setView(R.layout.sort_dialog)
            .setCancelable(false)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
        val btnConfirm = dialog.findViewById(R.id.btn_confirm) as Button
        val radioGroup = dialog.findViewById(R.id.radio_group_dialog) as RadioGroup

        btnConfirm.setOnClickListener {
            val message: String
            val sortType = when (radioGroup.checkedRadioButtonId) {
                R.id.by_default -> {
                    message = "Sort by default"
                    SortType.DEFAULT
                }
                R.id.by_priority -> {
                    message = "Sort by priority"
                    SortType.PRIORITY
                }
                R.id.by_deadline -> {
                    message = "Sort by deadline data"
                    SortType.DEADLINE
                }
                R.id.by_creation -> {
                    message = "Sort by creation data"
                    SortType.CREATION
                }
                else -> {
                    message = "The sorting type is not selected"
                    SortType.NOT_SELECTED
                }
            }
            showToast(message)
            APP.sortType = sortType
            viewModel.sortBy(sortType)
            dialog.dismiss()
        }
    }

    private fun showToast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()

    private fun updateTask(task: TaskModel, view: Int) {
        when (view) {
            Constants.IS_DONE -> task.isDone = !task.isDone
            Constants.IS_REMINDER -> task.isReminder = !task.isReminder
        }
        viewModel.save(task)
        updateList(viewModel.getList().value!!)
    }
}

