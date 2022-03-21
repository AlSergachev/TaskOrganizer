package com.example.taskorganizer.presentation.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.MainFragmentBinding
import com.example.taskorganizer.app.APP
import com.example.taskorganizer.app.App
import com.example.taskorganizer.presentation.Constants
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var listFactory: ListViewModelFactory

    @Suppress("PrivatePropertyName")
    private val NAME_FRAGMENT: String = "All Tasks"
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity?.applicationContext as App).appComponent.injectListFragment(this)
        initialization()
        setListener()
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this, listFactory)[ListViewModel::class.java]
        setActivityParam()
        adapter = ListAdapter()
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter

        viewModel.getList().observe(viewLifecycleOwner, Observer {newList ->
            Log.e(Constants.TAG, "set to adapter $newList")
            adapter.setList(newList)
            adapter.notifyDataSetChanged()
        })
    }
//    binding.recyclerView.layoutManager = LinearLayoutManager(this.context)


    private fun setListener() {
        binding.btnCreate.setOnClickListener {
            APP.toCreateFragment()
        }
        binding.btnUpdate.setOnClickListener{
            viewModel.getList().observe(viewLifecycleOwner, Observer {newList ->
                adapter.setList(newList)
                adapter.notifyDataSetChanged()
            })
        }
    }

    @Suppress("DEPRECATION")
    private fun setActivityParam() {
        APP.binding.btnDetailsTask.isClickable = true
        APP.binding.btnCreateTask.isClickable = true
        APP.binding.btnListTask.isClickable = false
        APP.binding.btnListTask.setBackgroundColor(resources.getColor(R.color.selected_green))
        APP.binding.title.text = NAME_FRAGMENT
    }

}