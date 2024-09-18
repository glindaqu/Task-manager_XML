package ru.glindaquint.taskmanager.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.glindaquint.taskmanager.database.AppDB
import ru.glindaquint.taskmanager.database.entities.TaskData

class MainViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val tasksDao = AppDB.getDatabase(application).getTasksDao()
    private val groupsDao = AppDB.getDatabase(application).getGroupsDao()

    fun getAllTasks(): LiveData<List<TaskData>> = tasksDao.getAllTasks()
}
