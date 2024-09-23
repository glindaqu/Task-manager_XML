package ru.glindaquint.taskmanager.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// if parent is 0 - parent of task is root element
// those tasks should be displayed in the separate recycler view
// in other cases parent is group id, in which task were created

@Entity
data class TaskData(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo val title: String? = null,
    @ColumnInfo val body: String? = null,
    @ColumnInfo val creationDate: Long = Date().time,
    @ColumnInfo val parent: Long = 0,
    @ColumnInfo val isDone: Boolean = false,
)
