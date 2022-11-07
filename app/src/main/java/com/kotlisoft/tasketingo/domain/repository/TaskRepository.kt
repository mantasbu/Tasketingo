package com.kotlisoft.tasketingo.domain.repository

import com.kotlisoft.tasketingo.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun addTask(task: Task)

    fun getAllTasksSortedByTitle(): Flow<List<Task>>

    suspend fun switchCompleteTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun deleteAllTasks()
}