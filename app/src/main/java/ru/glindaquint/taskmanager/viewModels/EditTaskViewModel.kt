package ru.glindaquint.taskmanager.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.glindaquint.taskmanager.database.entities.TaskData
import ru.glindaquint.taskmanager.repositories.TaskRepository

class EditTaskViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val taskRepository = TaskRepository(application)

    fun createTask(
        title: String?,
        body: String?,
        root: Long = 0,
        id: Long? = null,
    ) {
        viewModelScope.launch {
            taskRepository.create(title, body, root, id)
        }
    }

    fun getTask(id: Long): LiveData<TaskData> = taskRepository.get(id)
}
