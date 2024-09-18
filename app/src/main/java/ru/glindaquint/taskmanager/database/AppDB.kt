package ru.glindaquint.taskmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.glindaquint.taskmanager.database.dao.GroupsDao
import ru.glindaquint.taskmanager.database.dao.TasksDao
import ru.glindaquint.taskmanager.database.entities.GroupData
import ru.glindaquint.taskmanager.database.entities.TaskData

@Database(version = 1, entities = [TaskData::class, GroupData::class])
abstract class AppDB : RoomDatabase() {
    abstract fun getTasksDao(): TasksDao

    abstract fun getGroupsDao(): GroupsDao

    companion object {
        fun getDatabase(context: Context): AppDB =
            Room
                .databaseBuilder(
                    context = context,
                    klass = AppDB::class.java,
                    name = "taskManager.db",
                ).build()
    }
}
