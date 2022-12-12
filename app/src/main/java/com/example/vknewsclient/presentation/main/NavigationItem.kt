package com.example.vknewsclient.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vknewsclient.R
import com.example.vknewsclient.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
){
    object Home: NavigationItem(
        screen = Screen.Home,
        titleResId = R.string.home_text,
        icon = Icons.Outlined.Home
    )

    object Favorite: NavigationItem(
        screen = Screen.Favorite,
        titleResId = R.string.favorite_text,
        icon = Icons.Outlined.Favorite
    )

    object Profile: NavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.profile_text,
        icon = Icons.Outlined.Person
    )
}