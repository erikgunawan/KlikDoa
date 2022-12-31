package id.ergun.klikdoa.presentation.ui.navigation

/**
 * @author erikgunawan
 * Created 24/12/22 at 23.16
 */
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailDoa : Screen("home/{doaId}") {
        fun createRoute(doaId: String) = "home/$doaId"
    }
    object DetailFavoriteDoa : Screen("favorite/{doaId}") {
        fun createRoute(doaId: String) = "favorite/$doaId"
    }

}