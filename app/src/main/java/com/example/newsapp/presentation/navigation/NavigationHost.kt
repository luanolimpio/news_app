package com.example.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.core.navigation.CustomNavType.Companion.getCustomNavTypeMap
import com.example.newsapp.core.navigation.Routes
import com.example.newsapp.core.extensions.decode
import com.example.newsapp.core.extensions.encode
import com.example.newsapp.domain.models.Article
import com.example.newsapp.presentation.screens.details.DetailsScreen
import com.example.newsapp.presentation.screens.details.DetailsViewModel
import com.example.newsapp.presentation.screens.home.HomeScreen
import com.example.newsapp.presentation.screens.saved_articles.SavedArticlesScreen
import com.example.newsapp.presentation.screens.search.SearchScreen
import com.example.newsapp.presentation.screens.home.HomeViewModel
import com.example.newsapp.presentation.screens.saved_articles.SavedArticlesViewModel
import com.example.newsapp.presentation.screens.search.SearchViewModel

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home,
        modifier = modifier
    ) {
        composable<Routes.Home> {
            val viewModel = hiltViewModel<HomeViewModel>()
            val articles = viewModel.articles.collectAsLazyPagingItems()
            HomeScreen(
                articles = articles,
                onNavigateToDetails = { article ->
                    navController.navigate(Routes.Details(article = article.encode))
                },
            )
        }
        composable<Routes.Search> {
            val viewModel = hiltViewModel<SearchViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            SearchScreen(
                state = uiState,
                onEvent = viewModel::onEvent,
                onNavigateToDetails = { article ->
                    navController.navigate(Routes.Details(article = article.encode))
                },
            )
        }
        composable<Routes.SavedArticles> {
            val viewModel = hiltViewModel<SavedArticlesViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            SavedArticlesScreen(
                state = uiState,
                onEvent = viewModel::onEvent,
                onNavigateToDetails = { article ->
                    navController.navigate(Routes.Details(article = article.encode))
                },
            )
        }
        composable<Routes.Details>(typeMap = getCustomNavTypeMap(Article.serializer())) { backStackEntry ->
            val viewModel = hiltViewModel<DetailsViewModel>()
            val article = backStackEntry.toRoute<Routes.Details>().article.decode
            DetailsScreen(
                article = article,
                onEvent = viewModel::onEvent
            )
        }
    }
}