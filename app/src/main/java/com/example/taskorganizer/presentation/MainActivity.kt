@file:Suppress("DEPRECATION")

package com.example.taskorganizer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.MainActivityBinding
import com.example.taskorganizer.app.APP
import com.example.taskorganizer.app.App
import com.example.taskorganizer.data.basedata.TaskBaseData

class MainActivity : AppCompatActivity() {

    lateinit var binding: MainActivityBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as App).appComponent.inject(this)
        APP = this
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setListener()
    }


    private fun setListener() {
        binding.btnCreateTask.setOnClickListener {
            toCreateFragment()
        }
        binding.btnListTask.setOnClickListener {
            toListFragment()
        }
        binding.btnDetailsTask.setOnClickListener {
            toDetailsFragment(null)
        }
    }

    private fun resetColor() {
        binding.btnCreateTask.setBackgroundColor(resources.getColor(R.color.primary_green))
        binding.btnListTask.setBackgroundColor(resources.getColor(R.color.primary_green))
        binding.btnDetailsTask.setBackgroundColor(resources.getColor(R.color.primary_green))
    }

    fun toDetailsFragment(bundle: Bundle?){
        resetColor()
        navController.navigate(R.id.detailsFragment, bundle)
    }

    fun toCreateFragment(){
        resetColor()
        navController.navigate(R.id.createFragment)
    }

    fun toListFragment(){
        resetColor()
        navController.navigate(R.id.mainFragment)
    }
}