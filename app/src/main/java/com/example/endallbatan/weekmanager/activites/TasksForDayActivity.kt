package com.example.endallbatan.weekmanager.activites

import adapter.TaskAdapter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.example.endallbatan.weekmanager.R

class TasksForDayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_for_day_layout)
        actionBarSetup()
        initialise()
    }

    private fun actionBarSetup() {
        val weekday = intent.getStringExtra("weekday")
        title = "Tasks for $weekday"
    }

    private fun initialise() {
        taskList = findViewById<View>(R.id.tasksForDay) as ListView
        addTask = findViewById<View>(R.id.addTask) as Button

        addTask!!.setOnClickListener{(addTaskToDay())}

        var listItems = arrayOf("wäsche waschen","Avatar schauen", "Elmar T-shirts", "Kochen")
        val adapter = TaskAdapter(this@TasksForDayActivity,listItems)
        taskList!!.adapter = adapter
    }

    private fun addTaskToDay() {

    }

    private var taskList:ListView? = null
    private var addTask: Button? = null
}