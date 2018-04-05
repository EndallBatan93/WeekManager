package adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.endallbatan.weekmanager.R


class TaskAdapter(var activity: Activity, var taskList: Array<String>) : BaseAdapter() {
    @SuppressLint("ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val view:View = View.inflate(activity,R.layout.listview_task_element,null)
        val checkBox = view.findViewById<View>(R.id.done) as CheckBox
        val taskName = view.findViewById<View>(R.id.taskName) as TextView

        taskName.setTextColor(Color.WHITE)
        taskName.text = taskList[position]
        checkBox.highlightColor = Color.GREEN
        return view
    }

    override fun getItem(position: Int): Any {
        return taskList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return taskList.size
    }

}