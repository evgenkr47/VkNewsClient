package com.example.vknewsclient.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.navigation.AppNavGraph
import com.example.vknewsclient.navigation.NavigationState
import com.example.vknewsclient.navigation.Screen
import com.example.vknewsclient.navigation.rememberNavigationState
import com.example.vknewsclient.presentation.main.MainViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel){

    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            BottomNavigation{
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )
                items.forEach { item ->

                    BottomNavigationItem(
                        selected = currentRoute == item.screen.route,
                        onClick = {
                           navigationState.navigateTo(item.screen.route)
                                  },
                        icon = {
                            Icon(item.icon , contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    ) {
            AppNavGraph(
                navHostController = navigationState.navHostController,
                homeScreenContent = { HomeScreen(viewModel = viewModel)},
                favoriteScreenContent = { TextCounter(name = "Favorite") },
                profileScreenContent = { TextCounter(name = "Profile")}
            )
        }

    }

@Composable
private fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }

    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Color.Black
    )
}
