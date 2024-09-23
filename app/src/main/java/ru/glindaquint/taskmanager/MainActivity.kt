package ru.glindaquint.taskmanager

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import ru.glindaquint.taskmanager.adapters.TasksGridAdapter
import ru.glindaquint.taskmanager.databinding.ActivityMainBinding
import ru.glindaquint.taskmanager.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val tasksAdapter = TasksGridAdapter(this)
        viewModel.getAllTasks().observe(this) { tasksAdapter.update(it) }

        binding.tasksList.adapter = tasksAdapter

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            return@setOnApplyWindowInsetsListener insets
        }
        binding.toolbar.setOnMenuItemClickListener(
            Toolbar.OnMenuItemClickListener { item ->
                if (item.itemId == R.id.create_new_task) {
                    val intent = Intent(this, CreateTaskActivity::class.java)
                    startActivity(intent)
                }
                return@OnMenuItemClickListener true
            },
        )
    }
}
