package com.kotlisoft.tasketingo.data.repository

import com.kotlisoft.tasketingo.data.local.TaskDao
import com.kotlisoft.tasketingo.domain.model.Task
import com.kotlisoft.tasketingo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {

    override suspend fun addTask(task: Task) {
        dao.addTask(task)
    }

    override fun getAllTasksSortedByTitle(): Flow<List<Task>> {
        return dao.getAllTasksSortedByTitle()
    }

    override suspend fun switchCompleteTask(task: Task) {
        dao.switchCompleteTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }

    override suspend fun deleteAllTasks() {
        dao.deleteAllTasks()
    }
}