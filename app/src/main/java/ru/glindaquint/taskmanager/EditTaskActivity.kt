package ru.glindaquint.taskmanager

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import ru.glindaquint.taskmanager.databinding.ActivityCreateTaskBinding
import ru.glindaquint.taskmanager.support.IntentArgsNames
import ru.glindaquint.taskmanager.viewModels.EditTaskViewModel

class EditTaskActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCreateTaskBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[EditTaskViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            return@setOnApplyWindowInsetsListener insets
        }

        val editingTaskId = intent.getLongExtra(IntentArgsNames.TASK_ID, 0L)

        binding.createNewTask.setOnClickListener {
            viewModel.createTask(
                title =
                    binding.title.text
                        .toString()
                        .trim(),
                body =
                    binding.body.text
                        .toString()
                        .trim(),
                id = if (editingTaskId != 0L) editingTaskId else null,
            )
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        if (editingTaskId != 0L) {
            viewModel.getTask(editingTaskId).observe(this) {
                if (it != null) {
                    binding.title.setText(it.title ?: "")
                    binding.body.setText(it.body ?: "")
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        this.finish()
        return super.onSupportNavigateUp()
    }
}
