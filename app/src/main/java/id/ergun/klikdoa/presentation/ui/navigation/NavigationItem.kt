package id.ergun.klikdoa.presentation.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author erikgunawan
 * Created 24/12/22 at 23.16
 */
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)
