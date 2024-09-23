package ru.glindaquint.taskmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import ru.glindaquint.taskmanager.databinding.ActivityCreateTaskBinding
import ru.glindaquint.taskmanager.viewModels.CreateTaskViewModel

class CreateTaskActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCreateTaskBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[CreateTaskViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            return@setOnApplyWindowInsetsListener insets
        }
        binding.createNewTask.setOnClickListener {
            Log.d("", "onCreate: clicked")
            viewModel.createTask(
                title =
                    binding.title.text
                        .toString()
                        .trim(),
                body =
                    binding.body.text
                        .toString()
                        .trim(),
            )
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
