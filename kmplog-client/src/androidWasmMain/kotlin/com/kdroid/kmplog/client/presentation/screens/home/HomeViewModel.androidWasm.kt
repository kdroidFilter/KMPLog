package com.kdroid.kmplog.client.presentation.screens.home

import com.kdroid.kmplog.client.presentation.navigation.Destination
import com.kdroid.kmplog.client.presentation.navigation.Navigator

actual suspend fun navigateToSettings(navigator: Navigator) {
    navigator.navigate(Destination.Settings)
}