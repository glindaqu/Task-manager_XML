package ru.glindaquint.taskmanager.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.glindaquint.taskmanager.database.entities.TaskData
import ru.glindaquint.taskmanager.repositories.TaskRepository

class MainViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val taskRepository = TaskRepository(application)

    fun getAllTasks(): LiveData<List<TaskData>> = taskRepository.getAll()

    fun createTask(
        title: String?,
        body: String?,
        parentId: Long = 0L,
    ) {
        viewModelScope.launch {
            taskRepository.create(TaskData(title = title, body = body, parent = parentId))
        }
    }
}
