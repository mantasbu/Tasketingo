package com.kotlisoft.tasketingo.data.local

import androidx.room.*
import com.kotlisoft.tasketingo.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM task ORDER BY completed, title")
    fun getAllTasksSortedByTitle(): Flow<List<Task>>

    @Update
    suspend fun switchCompleteTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task")
    suspend fun deleteAllTasks()
}