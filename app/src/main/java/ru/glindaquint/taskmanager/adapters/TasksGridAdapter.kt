package ru.glindaquint.taskmanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ru.glindaquint.taskmanager.R
import ru.glindaquint.taskmanager.database.entities.TaskData
import ru.glindaquint.taskmanager.databinding.TaskBinding
import java.lang.ref.WeakReference

class TasksGridAdapter(
    context: Context,
) : ArrayAdapter<TaskData>(context, R.layout.task, mutableListOf()) {
    private var context: WeakReference<Context> = WeakReference(context)

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        if (context.get() == null) {
            throw Exception("Context is null : TaskAdapter")
        }
        val binding =
            if (convertView == null) {
                TaskBinding.inflate(
                    context
                        .get()!!
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                    parent,
                    false,
                )
            } else {
                TaskBinding.bind(convertView)
            }
        val task = getItem(position)
        binding.title.text = task?.title ?: "Null"
        return binding.root
    }

    fun update(items: List<TaskData>) {
        this.clear()
        items.forEach { this.add(it) }
        this.notifyDataSetChanged()
    }
}
