package com.example.newsapp.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.R
import com.example.newsapp.core.navigation.Routes
import com.example.newsapp.core.snackbar.SnackbarController
import com.example.newsapp.core.extensions.hasCurrentRoute
import com.example.newsapp.presentation.common.ObserveAsEvents
import com.example.newsapp.presentation.components.BottomNavigationBar
import com.example.newsapp.presentation.components.BottomNavigationItem
import kotlinx.coroutines.launch

@Composable
fun MainNavigator() {
    val scope = rememberCoroutineScope()
    val hostState = remember { SnackbarHostState() }
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    var isBottomBarVisible by rememberSaveable { (mutableStateOf(true)) }
    isBottomBarVisible = when {
        backStackEntry?.destination.hasCurrentRoute(Routes.Details::class) -> {
            false
        }

        else -> {
            true
        }
    }

    ObserveAsEvents(
        flow = SnackbarController.event,
        key1 = hostState
    ) { event ->
        scope.launch {
            hostState.currentSnackbarData?.dismiss()
            val result = hostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.name,
                duration = event.duration
            )
            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = hostState) },
        bottomBar = {
            AnimatedVisibility(
                visible = isBottomBarVisible,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                BottomNavigationBar(
                    items = listOf(
                        BottomNavigationItem(
                            label = stringResource(R.string.home),
                            route = Routes.Home,
                            icon = Icons.Default.Home,
                        ),
                        BottomNavigationItem(
                            label = stringResource(R.string.search),
                            route = Routes.Search,
                            icon = Icons.Default.Search,
                        ),
                        BottomNavigationItem(
                            label = stringResource(R.string.saved_articles),
                            route = Routes.SavedArticles,
                            icon = Icons.Default.Favorite,
                        ),
                    ),
                    navController = navController,
                )
            }
        }
    ) { innerPadding ->
        NavigationHost(navController = navController, Modifier.padding(innerPadding))
    }
}