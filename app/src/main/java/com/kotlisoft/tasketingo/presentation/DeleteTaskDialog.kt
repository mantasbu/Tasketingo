package com.kotlisoft.tasketingo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kotlisoft.tasketingo.R

@Composable
fun DeleteTaskDialog(
    onDismissRequest: () -> Unit,
    onConfirmClicked: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismissRequest()
        }
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colors.background)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.delete_all_tasks))
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = stringResource(id = R.string.cancel),
                    modifier = Modifier.clickable {
                        onDismissRequest()
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    onClick = {
                        onConfirmClicked()
                    }
                ) {
                    Text(text = stringResource(id = R.string.delete_all_tasks))
                }
            }
        }
    }
}