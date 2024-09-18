package ru.glindaquint.taskmanager.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroupData(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo val title: String? = null,
)
