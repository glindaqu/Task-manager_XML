package ru.glindaquint.taskmanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.glindaquint.taskmanager.database.entities.GroupData

@Dao
interface GroupsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(model: GroupData)

    @Delete
    suspend fun delete(model: GroupData)

    @Query("SELECT * FROM GroupData")
    fun getAllGroups(): LiveData<List<GroupData>>

    @Query("SELECT * FROM GroupData WHERE id = :id LIMIT 1")
    fun get(id: Long): LiveData<GroupData>
}
