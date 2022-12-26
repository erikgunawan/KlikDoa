package id.ergun.klikdoa.presentation.feature.splash

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.presentation.feature.doa.DoaScreen
import id.ergun.klikdoa.presentation.feature.doa.detail.DetailScreen
import id.ergun.klikdoa.presentation.ui.navigation.Screen

/**
 * @author erikgunawan
 * Created 26/12/22 at 11.17
 */

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val coroutineScope = rememberCoroutineScope()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                DoaScreen(
                    navigateToDetail = { doaId ->
                        navController.navigate(Screen.DetailDoa.createRoute(doaId))
                    }
                )
            }
            composable(Screen.Cart.route) {
                val context = LocalContext.current
//                DoaScreen(
//                    onOrderButtonClicked = { message ->
//                        shareOrder(context, message)
//                    }
//                )
            }
            composable(Screen.Profile.route) {
//                DoaScreen()
            }
            composable(
                route = Screen.DetailDoa.route,
                arguments = listOf(navArgument("doaId") { type = NavType.LongType }),
            ) {
                val doa: Doa = it.arguments?.getParcelable("doa") ?: Doa.generateDefaultDoa()
                DetailScreen(
                    doaId = doa.id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}