package com.awesome.compose.firstApp.settings


/**
 * Created by Awesome Jim on.
 * 02/10/2023
 */

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Preview(showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetWithVerticalActions() {
    val state = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        state.show()
    }

    if (state.isVisible) {
        ModalBottomSheet(
            windowInsets = WindowInsets(0.dp),
            onDismissRequest = {
                scope.launch {
                    state.hide()
                }
            }) {
            val items = listOf(
                Icons.Rounded.Share to "Share",
                Icons.Rounded.Link to "Get Link",
                Icons.Rounded.Edit to "Edit name",
                Icons.Rounded.Delete to "Delete items",
            )
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Column(Modifier.navigationBarsPadding()) {
                    items.forEach { item ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(32.dp),
                            modifier = Modifier
                                .clickable { /* TODO */ }
                                .clip(MaterialTheme.shapes.medium)
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp, vertical = 18.dp),
                        ) {
                            Icon(item.first, null)
                            Text(item.second)
                        }
                    }
                }
            }
        }
    }
}