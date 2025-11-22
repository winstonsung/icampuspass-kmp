package app.icampuspass

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import app.icampuspass.views.composables.ModalDrawerSheet
import app.icampuspass.views.navigation.NavDisplay
import app.icampuspass.views.navigation.navkeys.AboutScreenNavKey
import app.icampuspass.views.navigation.navkeys.MainScreenNavKey
import app.icampuspass.views.navigation.navkeys.SettingsScreenNavKey
import app.icampuspass.views.theme.Theme
import kotlinx.coroutines.launch

@Composable
fun App() {
    Theme {
        Surface(color = Color.Black) {
            Surface(modifier = Modifier.safeDrawingPadding()) {
                val scope = rememberCoroutineScope()

                val backStack = remember { mutableStateListOf<NavKey>(MainScreenNavKey) }

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet(
                            backStack = backStack,
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
                                    backStack.removeLastOrNull()
                                    backStack.add(MainScreenNavKey)
                                }
                            },
                            onNavigateSettings = dropUnlessResumed {
                                scope.launch {
                                    drawerState.close()
                                }

                                scope.launch {
                                    backStack.removeLastOrNull()
                                    backStack.add(SettingsScreenNavKey)
                                }
                            },
                            onNavigateAbout = dropUnlessResumed {
                                scope.launch {
                                    drawerState.close()
                                }

                                scope.launch {
                                    backStack.removeLastOrNull()
                                    backStack.add(AboutScreenNavKey)
                                }
                            }
                        )
                    },
                    drawerState = drawerState,
                    content = {
                        NavDisplay(
                            backStack = backStack,
                            scope = scope,
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
