package id.ergun.klikdoa.presentation.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.ergun.klikdoa.R
import id.ergun.klikdoa.presentation.feature.doa.DoaScreen
import id.ergun.klikdoa.presentation.feature.doa.detail.DetailScreen
import id.ergun.klikdoa.presentation.feature.doa.favorite.DoaFavoriteScreen
import id.ergun.klikdoa.presentation.feature.profile.ProfileScreen
import id.ergun.klikdoa.presentation.feature.splash.SplashScreen
import id.ergun.klikdoa.presentation.ui.navigation.NavigationItem
import id.ergun.klikdoa.presentation.ui.navigation.Screen

/**
 * @author erikgunawan
 * Created 24/12/22 at 23.05
 */
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailDoa.route &&
                currentRoute != Screen.Splash.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(navController = navController)
            }
            composable(Screen.Home.route) {
                DoaScreen(
                    navigateToDetail = { doaId ->
                        navController.navigate(Screen.DetailDoa.createRoute(doaId))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                DoaFavoriteScreen(
                    navigateToDetail = { doaId ->
                        navController.navigate(Screen.DetailDoa.createRoute(doaId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(
                route = Screen.DetailDoa.route,
                arguments = listOf(navArgument("doaId") { type = NavType.StringType }),
            ) {
                val doaId: String = it.arguments?.getString("doaId") ?: ""
                DetailScreen(
                    doaId = doaId,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_doa),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}