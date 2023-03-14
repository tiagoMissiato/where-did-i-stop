package com.tiagomissiato.wheredidistop

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tiagomissiato.wheredidistop.core.ui.theme.WhereDidIStopTheme
import com.tiagomissiato.wheredidistop.movie.popular.presenter.PopularMovieListScreen
import com.tiagomissiato.wheredidistop.tvshow.popular.presenter.PopularTvShowList
import dagger.hilt.android.AndroidEntryPoint

sealed class Screen(val route: String, val name: String, val icon: ImageVector) {
    object Movie : Screen("movie", "Movies", Icons.Outlined.Movie)
    object TvShow : Screen("tvshow", "Tv Shows", Icons.Outlined.Tv)
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WhereDidIStopTheme() {
                MainScreen()
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar( {} ) },
        bottomBar = {
            AppBottomNavigation(navController)
        }
    ){ innerPadding ->
        NavigationGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(clearCache: () -> Unit) {
    TopAppBar(
        title = { Text("Where did I stop") },
        navigationIcon = { },
        actions = {
            // RowScope here, so these icons will be placed horizontally
            IconButton(onClick = { clearCache() }) {
                Icon(Icons.Outlined.Refresh, contentDescription = "Localized description")
            }
        }
    )
}

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController, startDestination = Screen.Movie.route, modifier = modifier) {
        composable(Screen.Movie.route) {
            PopularMovieListScreen()
        }
        composable(Screen.TvShow.route) {
            PopularTvShowList()
        }
    }
}

@Composable
fun AppBottomNavigation(navController: NavController) {
    val items = listOf(
        Screen.Movie,
        Screen.TvShow,
    )
    BottomNavigation(
        modifier = Modifier,
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = contentColorFor(backgroundColor),
        elevation = BottomNavigationDefaults.Elevation,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(screen.name) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }

}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun DefaultPreview() {
    WhereDidIStopTheme {
        MainScreen()
    }
}