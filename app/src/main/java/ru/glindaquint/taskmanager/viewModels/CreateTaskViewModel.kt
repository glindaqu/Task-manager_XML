package ru.glindaquint.taskmanager.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.glindaquint.taskmanager.repositories.TaskRepository

class CreateTaskViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val taskRepository = TaskRepository(application)

    fun createTask(
        title: String?,
        body: String?,
        root: Long = 0,
    ) {
        viewModelScope.launch {
            taskRepository.create(title, body, root)
        }
    }
}
