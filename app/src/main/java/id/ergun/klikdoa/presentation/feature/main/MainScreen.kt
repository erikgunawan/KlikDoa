package id.ergun.klikdoa.presentation.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.ergun.klikdoa.presentation.feature.base.BottomNavigationBar
import id.ergun.klikdoa.presentation.feature.doa.DoaScreen
import id.ergun.klikdoa.presentation.feature.doa.detail.DoaDetailScreen
import id.ergun.klikdoa.presentation.feature.doa.favorite.DoaFavoriteScreen
import id.ergun.klikdoa.presentation.feature.profile.ProfileScreen
import id.ergun.klikdoa.presentation.feature.splash.SplashScreen
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
                BottomNavigationBar(navController)
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
                        navController.navigate(Screen.DetailFavoriteDoa.createRoute(doaId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navigateBack = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Profile.route) {
                                inclusive = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(
                route = Screen.DetailDoa.route,
                arguments = listOf(navArgument("doaId") { type = NavType.StringType }),
            ) {
                val doaId: String = it.arguments?.getString("doaId") ?: ""
                DoaDetailScreen(
                    doaId = doaId,
                    navigateBack = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.DetailDoa.route) {
                                inclusive = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(
                route = Screen.DetailFavoriteDoa.route,
                arguments = listOf(navArgument("doaId") { type = NavType.StringType }),
            ) {
                val doaId: String = it.arguments?.getString("doaId") ?: ""
                DoaDetailScreen(
                    doaId = doaId,
                    navigateBack = {
                        navController.navigate(Screen.Favorite.route) {
                            popUpTo(Screen.DetailFavoriteDoa.route) {
                                inclusive = true
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