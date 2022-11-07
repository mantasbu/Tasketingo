package com.kotlisoft.tasketingo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlisoft.tasketingo.domain.model.Task
import com.kotlisoft.tasketingo.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _tasksViewState = MutableStateFlow(TasksViewState())
    val tasksViewState: StateFlow<TasksViewState> = _tasksViewState

    init {
        getAllTasksSortedByTitle()
    }

    fun addTask(task: Task) = viewModelScope.launch {
        repository.addTask(task)
        getAllTasksSortedByTitle()
    }

    private fun getAllTasksSortedByTitle() = viewModelScope.launch {
        repository.getAllTasksSortedByTitle().collect { tasks ->
            _tasksViewState.value = TasksViewState(tasks = tasks)
        }
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.deleteTask(task)
        getAllTasksSortedByTitle()
    }

    fun deleteAllTasks() = viewModelScope.launch {
        _tasksViewState.value = TasksViewState()
        repository.deleteAllTasks()
    }

    fun switchCompleteTask(task: Task, completed: Boolean) = viewModelScope.launch {
        repository.switchCompleteTask(task.copy(completed = completed))
        getAllTasksSortedByTitle()
    }
}