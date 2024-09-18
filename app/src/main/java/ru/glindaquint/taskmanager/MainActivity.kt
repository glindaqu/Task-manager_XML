package ru.glindaquint.taskmanager

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import ru.glindaquint.taskmanager.adapters.TasksGridAdapter
import ru.glindaquint.taskmanager.databinding.ActivityMainBinding
import ru.glindaquint.taskmanager.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel =
            ViewModelProvider(this)[MainViewModel::class.java]
        val tasksAdapter = TasksGridAdapter(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel.getAllTasks().observe(this) { tasksAdapter.update(it) }

        setContentView(binding.root)

        binding.tasksList.adapter = tasksAdapter

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            return@setOnApplyWindowInsetsListener insets
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.create_new_task) {
        } else if (item.itemId == R.id.create_new_group) {
        }
        return super.onOptionsItemSelected(item)
    }
}
