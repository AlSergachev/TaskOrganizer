
package com.example.taskorganizer.presentation.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.MainFragmentBinding
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.GetTaskListUseCase
import com.example.taskorganizer.presentation.Constants
import com.example.taskorganizer.presentation.app.APP

class ListFragment : Fragment() {

    @Suppress("PrivatePropertyName")
    private val NAME_FRAGMENT: String = "All Tasks"

    private lateinit var viewModel: ListViewModel
    private lateinit var binding: MainFragmentBinding
    private val getTaskListUseCase = GetTaskListUseCase()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
        setListener()
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        setActivityParam()
        val adapter =  ListAdapter()
        binding.recyclerView.adapter = adapter
        adapter.setList(getTaskListUseCase.execute())

    }



    private fun setActivityParam() {
        APP.binding.btnDetailsTask.isClickable = true
        APP.binding.btnCreateTask.isClickable = true
        APP.binding.btnListTask.isClickable = false
        APP.binding.btnListTask.setBackgroundColor(resources.getColor(R.color.selected_green))
        APP.binding.title.text = NAME_FRAGMENT
    }

    private fun setListener() {
        binding.btnCreate.setOnClickListener {
            APP.toCreateFragment()
        }
    }


    companion object {
        fun clickTask(task: TaskModel) {
            val bundle = Bundle()
            bundle.putParcelable(Constants.KEY_TASK, task)
            APP.toDetailsFragment(bundle)
        }
    }



//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}