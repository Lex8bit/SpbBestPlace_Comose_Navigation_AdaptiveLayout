package com.example.spb_bestplacenavigationadaptivelayout

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.spb_bestplacenavigationadaptivelayout.data.PlacesDataProvider
import com.example.spb_bestplacenavigationadaptivelayout.ui.CategoryScreen
import com.example.spb_bestplacenavigationadaptivelayout.ui.DetailScreen
import com.example.spb_bestplacenavigationadaptivelayout.ui.PlacesScreenAndDetail
import com.example.spb_bestplacenavigationadaptivelayout.ui.PlacesScreenListOnly

enum class PlacesScreens(@StringRes val title: Int) {
    Category(title = R.string.Category),
    Places(title = R.string.Places),
    Description(title = R.string.Description)
}

/**
 * Content shown depending on size and state of device.
 */
enum class PlacesContentType {
    ListOnly, ListAndDetail
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BestPlacesApp(
    windowSize: WindowWidthSizeClass
) {
    val viewModel: SpbBestPlacesViewModel = viewModel()
    val navController: NavHostController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState() //чтобы проверить есть ли в бекстеке экраны
    val currentScreen = PlacesScreens.valueOf(
        backStackEntry?.destination?.route ?: PlacesScreens.Category.name
    )

    val contentType: PlacesContentType
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            contentType = PlacesContentType.ListOnly
        }
        WindowWidthSizeClass.Medium -> {
            contentType = PlacesContentType.ListAndDetail
        }
        WindowWidthSizeClass.Expanded -> {
            contentType = PlacesContentType.ListAndDetail
        }
        else -> {
            contentType = PlacesContentType.ListOnly
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = PlacesScreens.Category.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = PlacesScreens.Category.name) {
                CategoryScreen(
                    places = PlacesDataProvider.allPlaces.map{ it.placesCategory }.toSet().toList(),
                    onCategoryClick = {
                        viewModel.pickCategory(it)
                        navController.navigate(PlacesScreens.Places.name)
                    }
                )
            }
            if (contentType == PlacesContentType.ListAndDetail) {
                composable(route = PlacesScreens.Places.name) {
                    PlacesScreenAndDetail(
                        places = PlacesDataProvider.allPlaces.filter { it.placesCategory == uiState.category },
                        onNameClick = {
                            viewModel.pickPlace(it)
                        },
                        onClick = {
                            navController.popBackStack(
                                PlacesScreens.Category.name,
                                inclusive = false
                            )
                        },
                        uiState = uiState
                    )
                }
            }
            else{
                composable(route = PlacesScreens.Places.name) {
                    PlacesScreenListOnly(
                        places = PlacesDataProvider.allPlaces.filter { it.placesCategory == uiState.category },
                        onNameClick = {
                            viewModel.pickPlace(it)
                            navController.navigate(PlacesScreens.Description.name)
                        }
                    )
                }
                composable(route = PlacesScreens.Description.name) {
                    DetailScreen(
                        uiState = uiState,
                        onClick = {
                            navController.popBackStack(PlacesScreens.Category.name, inclusive = false)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: PlacesScreens,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title))  },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
    )
}


