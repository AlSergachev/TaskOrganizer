@file:Suppress("DEPRECATION")

package com.example.taskorganizer.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.taskorganizer.R
import com.example.taskorganizer.databinding.MainActivityBinding
import com.example.taskorganizer.app.APP
import com.example.taskorganizer.app.App
import com.example.taskorganizer.presentation.utils.Notify

class MainActivity : AppCompatActivity() {

    lateinit var binding: MainActivityBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (applicationContext as App).appComponent.inject(this)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        APP = this


    }

    fun toDetailsFragment(bundle: Bundle?){
        navController.navigate(R.id.detailsFragment, bundle)
    }

    fun toCreateFragment(){
        navController.navigate(R.id.createFragment)
    }

    fun toListFragment(){
        navController.navigate(R.id.mainFragment)
    }

    fun toast(notify: Notify){
        val test = when (notify) {
            Notify.EMPTY_TASK -> "Вы не можете сохранить пустую задачу"
            Notify.ERROR_SAVE -> "Задача не сохранена"
            Notify.SUCCESS_SAVE -> "Задача успешно сохранена"
            Notify.ERROR_DELETE -> "Задача не удалена"
            Notify.SUCCESS_DELETE -> "Задача успешно удалена"
            Notify.EQUAL_TASKS -> "Задача не сохранена. Вы не изменили задачу"
        }
        Toast.makeText(APP.applicationContext, test, Toast.LENGTH_LONG).show()
    }
}