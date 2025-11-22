package app.icampuspass

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.compose.rememberNavController
import app.icampuspass.views.composables.ModalDrawerSheet
import app.icampuspass.views.navigation.NavHost
import app.icampuspass.views.navigation.destinations.AboutScreenDestination
import app.icampuspass.views.navigation.destinations.MainScreenDestination
import app.icampuspass.views.navigation.destinations.SettingsScreenDestination
import app.icampuspass.views.theme.Theme
import kotlinx.coroutines.launch

@Composable
fun App() {
    Theme {
        Surface(color = Color.Black) {
            Surface(modifier = Modifier.safeDrawingPadding()) {
                val scope = rememberCoroutineScope()

                val navController = rememberNavController()

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet(
                            navController = navController,
                            onMenuClose = {
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            onNavigateMain = dropUnlessResumed {
                                scope.launch {
                                    drawerState.close()
                                }

                                scope.launch {
                                    navController.navigate(route = MainScreenDestination) {
                                        popUpTo(route = MainScreenDestination)

                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            onNavigateSettings = dropUnlessResumed {
                                scope.launch {
                                    drawerState.close()
                                }

                                scope.launch {
                                    navController.navigate(route = SettingsScreenDestination) {
                                        popUpTo(route = MainScreenDestination)

                                        launchSingleTop = true
                                    }
                                }
                            },
                            onNavigateAbout = dropUnlessResumed {
                                scope.launch {
                                    drawerState.close()
                                }

                                scope.launch {
                                    navController.navigate(route = AboutScreenDestination) {
                                        popUpTo(route = MainScreenDestination)

                                        launchSingleTop = true
                                    }
                                }
                            }
                        )
                    },
                    drawerState = drawerState,
                    content = {
                        NavHost(
                            scope = scope,
                            navController = navController,
                            drawerState = drawerState
                        )
                    }
                )

                BackHandler(enabled = drawerState.isOpen) {
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
        }
    }
}
