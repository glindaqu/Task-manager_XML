package ru.glindaquint.taskmanager.database.dao

import androidx.lifecycle.MutableLiveData
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
    fun getAllTasks(): MutableLiveData<List<TaskData>>

    @Query("SELECT * FROM TaskData WHERE id = :id LIMIT 1")
    fun get(id: Long): MutableLiveData<TaskData>
}
