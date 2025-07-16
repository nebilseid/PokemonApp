package com.example.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.common.LabelRes

@Composable
fun InfoRow(label: LabelRes, value: Any, modifier: Modifier = Modifier) {
    Text(
        text = when (value) {
            is Int -> stringResource(id = label.resId, value)
            is String -> stringResource(id = label.resId, value)
            else -> stringResource(id = label.resId, value.toString())
        },
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier.padding(vertical = 4.dp)
    )
}
