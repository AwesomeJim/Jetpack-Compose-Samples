/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * View Model to retrieve all items in the Room database.
 */
class HomeViewModel(itemsRepository: ItemsRepository) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    /**
     * Home ui state
     * he recommended way to expose a Flow from a ViewModel is with a StateFlow. Using a StateFlow allows the data to be saved and observed, regardless of the UI lifecycle.
     * To convert a Flow to a StateFlow, you use the stateIn operator.
     *
     * The stateIn operator has three parameters which are explained below:
     *
     * scope - The viewModelScope defines the lifecycle of the StateFlow. When the viewModelScope is canceled, the StateFlow is also canceled.
     * started - The pipeline should only be active when the UI is visible. The SharingStarted.WhileSubscribed() is used to accomplish this.
     * To configure a delay (in milliseconds) between the disappearance of the last subscriber and the stopping of the sharing coroutine,
     * pass in the TIMEOUT_MILLIS to the SharingStarted.WhileSubscribed() method.
     * initialValue - Set the initial value of the state flow to HomeUiState().
     * Once you've converted your Flow into a StateFlow, you can collect it using the collectAsState() method, converting its data into State of the same type.
     */
    val homeUiState: StateFlow<HomeUiState> =
        itemsRepository.getAllItemsStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val itemList: List<Item> = listOf())
