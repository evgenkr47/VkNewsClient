package com.example.vknewsclient.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vknewsclient.navigation.AppNavGraph
import com.example.vknewsclient.navigation.rememberNavigationState
import com.example.vknewsclient.presentation.comments.CommentsScreen
import com.example.vknewsclient.presentation.news.NewsFeedScreen

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(){

    val navigationState = rememberNavigationState()


    Scaffold(
        bottomBar = {
            BottomNavigation{
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )
                items.forEach { item ->

                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
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
                newsFeedScreenContent = {
                        NewsFeedScreen(
                    onCommentClickListener = {
                        navigationState.navigateToComments(it)
                    }
                )
                    },
                favoriteScreenContent = { TextCounter(name = "Favorite") },
                profileScreenContent = { TextCounter(name = "Profile") },
                commentsScreenContent = { feedPost ->
                    CommentsScreen (
                        onBackPressed = {
                            navigationState.navHostController.popBackStack()
                        },
                        feedPost = feedPost
                    )
                }
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
