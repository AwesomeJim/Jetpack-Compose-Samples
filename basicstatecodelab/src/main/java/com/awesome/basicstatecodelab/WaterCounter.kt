package com.awesome.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A stateless composable is a composable that doesn't own any state, meaning it doesn't hold or define or modify new state.

A stateful composable is a composable that owns a piece of state that can change over time.

In real apps, having a 100% stateless composable can be difficult to achieve depending on the composable's responsibilities.
You should design your composables in a way that they will own as little state as possible and allow the state to be hoisted,
when it makes sense, by exposing it in the composable's API.

 */


@Composable
fun StatelessCounter(
    count: Int, onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (count > 0) {
            // This text is present if the button has been clicked
            // at least once; absent otherwise
            Text(
                text = "You've had $count glasses.",
                modifier = modifier.padding(16.dp)
            )
        }
        Button(
            onClick = onIncrement,
            enabled = count < 10,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add one")
        }

    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count, { count++ }, modifier)
}


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2, widthDp = 360, heightDp = 640)
@Composable
fun WellnessScreenPreview() {
    WellnessScreen()
}