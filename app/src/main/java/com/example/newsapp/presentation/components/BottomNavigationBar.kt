package com.example.newsapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.R
import com.example.newsapp.core.navigation.Routes
import com.example.newsapp.core.extensions.hasCurrentRoute

data class BottomNavigationItem(val label: String, val icon: ImageVector, val route: Routes)

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    navController: NavController,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = backStackEntry?.destination.hasCurrentRoute(item.route::class),
                label = { Text(text = item.label) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
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
        ), navController = rememberNavController()
    )
}

