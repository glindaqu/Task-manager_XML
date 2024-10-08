package ru.glindaquint.taskmanager.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.ViewModelProvider
import ru.glindaquint.taskmanager.EditTaskActivity
import ru.glindaquint.taskmanager.R
import ru.glindaquint.taskmanager.database.entities.TaskData
import ru.glindaquint.taskmanager.databinding.TaskBinding
import ru.glindaquint.taskmanager.support.IntentArgsNames
import ru.glindaquint.taskmanager.viewModels.EditTaskViewModel
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat

class TasksGridAdapter(
    context: Context,
) : ArrayAdapter<TaskData>(context, R.layout.task, mutableListOf()) {
    private var context: WeakReference<Context> = WeakReference(context)
    private val editTaskViewModel =
        ViewModelProvider(context as AppCompatActivity)[EditTaskViewModel::class.java]

    @SuppressLint("SimpleDateFormat")
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
        binding.title.text = task?.title ?: "Task #${task?.id}"
        binding.body.text = task?.body ?: "The task body is empty..."
        binding.creationDate.text = SimpleDateFormat("dd.MM.yyyy").format(task?.creationDate)
        binding.isDone.isChecked = task?.isDone ?: false

        binding.root.setOnClickListener {
            val intent = Intent(context.get(), EditTaskActivity::class.java)
            intent.putExtra(IntentArgsNames.TASK_ID, task?.id)
            context.get()?.startActivity(intent)
        }

        binding.isDone.setOnClickListener { view ->
            if (view != null && task?.id != null) {
                editTaskViewModel.changeDone(task.id, (view as SwitchCompat).isChecked)
            }
        }

        return binding.root
    }

    fun update(items: List<TaskData>) {
        this.clear()
        items.forEach { this.add(it) }
        this.notifyDataSetChanged()
    }
}
