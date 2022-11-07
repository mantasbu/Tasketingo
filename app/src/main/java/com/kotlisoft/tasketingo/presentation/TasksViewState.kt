package com.kotlisoft.tasketingo.presentation

import com.kotlisoft.tasketingo.domain.model.Task

data class TasksViewState(
    val tasks: List<Task> = emptyList()
)