package com.levp.dailyreminder.classes

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem (
    val title: String,
    val icon: ImageVector,
    val screen: @Composable () -> Unit
)
