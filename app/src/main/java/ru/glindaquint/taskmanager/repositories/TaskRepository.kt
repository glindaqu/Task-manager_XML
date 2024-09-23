package ru.glindaquint.taskmanager.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import ru.glindaquint.taskmanager.database.AppDB
import ru.glindaquint.taskmanager.database.entities.TaskData
import java.util.Date

class TaskRepository(
    application: Application,
) {
    private val dao = AppDB.getDatabase(application).getTasksDao()

    suspend fun create(
        title: String?,
        body: String?,
        root: Long = 0,
        id: Long? = null,
    ) {
        dao.add(
            TaskData(
                id = id,
                title = title,
                body = body,
                creationDate = Date().time,
                parent = root,
            ),
        )
    }

    suspend fun create(model: TaskData) {
        dao.add(model)
    }

    suspend fun delete(model: TaskData) {
        dao.delete(model)
    }

    fun get(id: Long): LiveData<TaskData> = dao.get(id)

    fun getAll(): LiveData<List<TaskData>> = dao.getAllTasks()

    suspend fun setDone(id: Long) {
        dao.setDone(id)
    }

    suspend fun setUndone(id: Long) {
        dao.setUndone(id)
    }
}
