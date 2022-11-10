package com.kotlisoft.tasketingo.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotlisoft.tasketingo.R
import com.kotlisoft.tasketingo.domain.model.Task

@Composable
fun TasksScreen(
    viewModel: TaskViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var title by rememberSaveable {
        mutableStateOf("")
    }
    var showDeleteAllTasksDialog by rememberSaveable {
        mutableStateOf(false)
    }

    val blankTitleError = stringResource(id = R.string.blank_title)

    val tasksViewState by viewModel.tasksViewState.collectAsState()

    if (showDeleteAllTasksDialog) {
        DeleteTaskDialog(
            onDismissRequest = {
                showDeleteAllTasksDialog = false
            },
            onConfirmClicked = {
                viewModel.deleteAllTasks()
                showDeleteAllTasksDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    if (tasksViewState.tasks.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                showDeleteAllTasksDialog = true
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = stringResource(id = R.string.delete_all_tasks),
                                tint = MaterialTheme.colors.onSurface
                            )
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
            )
        },
        content = { paddingValues ->
            if (tasksViewState.tasks.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()
                        )
                ) {
                    items(items = tasksViewState.tasks) { task ->
                        TaskItem(
                            task = task,
                            onSwitchCompleteTask = { task, completed ->
                                viewModel.switchCompleteTask(
                                    task = task,
                                    completed = completed
                                )
                            },
                            onDeleteTask = { taskToDelete ->
                                viewModel.deleteTask(task = taskToDelete)
                            }
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.no_tasks),
                        fontSize = 20.sp
                    )
                }
            }
        },
        bottomBar = {
            Row {
                OutlinedTextField(
                    value = title,
                    maxLines = 1,
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    placeholder = {
                        Text(text = stringResource(R.string.enter_new_task))
                    },
                    onValueChange = {
                        title = it
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default),
                    keyboardActions = KeyboardActions {
                        if (title.isNotBlank()) {
                            viewModel.addTask(Task(title = title))
                            title = ""
                        } else {
                            Toast.makeText(context, blankTitleError, Toast.LENGTH_SHORT).show()
                        }
                    },
                )

                FloatingActionButton(
                    modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                    onClick = {
                        if (title.isNotBlank()) {
                            viewModel.addTask(Task(title = title))
                            title = ""
                        } else {
                            Toast.makeText(context, blankTitleError, Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_task)
                    )
                }
            }
        }
    )
}