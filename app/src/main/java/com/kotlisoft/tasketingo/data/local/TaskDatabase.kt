package com.kotlisoft.tasketingo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlisoft.tasketingo.domain.model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {

    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "tasks_db"
    }
}