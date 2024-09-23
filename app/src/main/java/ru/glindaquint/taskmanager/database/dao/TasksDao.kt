package ru.glindaquint.taskmanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.glindaquint.taskmanager.database.entities.TaskData

@Dao
interface TasksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(model: TaskData)

    @Delete
    suspend fun delete(model: TaskData)

    @Query("SELECT * FROM TaskData")
    fun getAllTasks(): LiveData<List<TaskData>>

    @Query("SELECT * FROM TaskData WHERE id = :id LIMIT 1")
    fun get(id: Long): LiveData<TaskData>

    @Query("UPDATE TaskData SET isDone = 1 WHERE id = :id")
    suspend fun setDone(id: Long)

    @Query("UPDATE TaskData SET isDone = 0 WHERE id = :id")
    suspend fun setUndone(id: Long)
}
