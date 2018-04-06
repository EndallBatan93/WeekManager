package com.example.endallbatan.weekmanager.activites

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.endallbatan.weekmanager.R
import com.google.firebase.database.*
import entities.Task


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
        TAG = "TasksForDayActivity"
        val database:FirebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference:DatabaseReference = database.getReference("Tasks")
        databaseReference.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                Log.e(TAG,"Tasks could not be retrieved from Database")
            }

            override fun onDataChange(snapshot: DataSnapshot?) {
                val s = snapshot!!.getValue() as HashMap<*, *>
            }

        })

        taskList = findViewById<View>(R.id.tasksForDay) as ListView
        addTask = findViewById<View>(R.id.addTask) as Button

        addTask!!.setOnClickListener { (addTaskToDay(databaseReference)) }

//        val listItems = arrayOf(name)
//        val adapter = TaskAdapter(this@TasksForDayActivity,listItems)
//        taskList!!.adapter = adapter
    }

    private fun addTaskToDay(databaseReference: DatabaseReference) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.add_task_dialog, null)
        dialogBuilder.setView(dialogView)

        val taskName = dialogView.findViewById<View>(R.id.nameOfTask) as EditText
        val taskDescription = dialogView.findViewById<View>(R.id.descriptionOfTask) as EditText
        val addButton = dialogView.findViewById<View>(R.id.add) as Button

        val b = dialogBuilder.create()
        addButton.setOnClickListener { (addTaskToList(taskName, taskDescription, b, databaseReference)) }
        b.show()
    }

    private fun addTaskToList(taskName: EditText, taskDescription: EditText, dialog: AlertDialog, databaseReference: DatabaseReference) {
        val task = Task(taskName.text.toString(), taskDescription.text.toString())
        val newRef = databaseReference.child("Tasks").push()
        newRef.setValue(task)
        dialog.dismiss()
    }

    private var taskList: ListView? = null
    private var addTask: Button? = null
    private var listItems:Array<String>? = null
    private var TAG:String? = null

}