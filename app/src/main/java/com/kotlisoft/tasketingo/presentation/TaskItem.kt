package com.kotlisoft.tasketingo.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlisoft.tasketingo.R
import com.kotlisoft.tasketingo.domain.model.Task

@Composable
fun TaskItem(
    task: Task,
    onSwitchCompleteTask: (task: Task, completed: Boolean) -> Unit,
    onDeleteTask: (Task) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onSwitchCompleteTask(task, !task.completed)
                }
        ) {
            Checkbox(
                checked = task.completed,
                onCheckedChange = { checked ->
                    onSwitchCompleteTask(task, checked)
                }
            )
            Text(
                text = task.title,
                fontSize = 24.sp,
                style = if (task.completed) {
                    TextStyle(textDecoration = TextDecoration.LineThrough)
                } else {
                    LocalTextStyle.current
                },
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete_all_tasks),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onDeleteTask(task)
                    }
            )
        }
    }
}