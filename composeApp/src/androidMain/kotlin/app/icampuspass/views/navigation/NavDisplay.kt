package app.icampuspass.views.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import app.icampuspass.views.navigation.navkeys.AboutScreenNavKey
import app.icampuspass.views.navigation.navkeys.MainScreenNavKey
import app.icampuspass.views.navigation.navkeys.SettingsScreenNavKey
import app.icampuspass.views.navigation.navkeys.UserPickerNavKey
import app.icampuspass.views.screens.AboutScreen
import app.icampuspass.views.screens.MainScreen
import app.icampuspass.views.screens.SettingsScreen
import app.icampuspass.views.screens.UserPickerScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavDisplay(
    modifier: Modifier = Modifier,
    backStack: SnapshotStateList<NavKey> = remember { mutableStateListOf<NavKey>(MainScreenNavKey) },
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    NavDisplay(
        backStack = backStack,
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        entryProvider = entryProvider {
            entry<MainScreenNavKey> {
                MainScreen(
                    onMenuOpen = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    onNavigateUserPicker = {
                        scope.launch {
                            drawerState.close()
                        }

                        scope.launch {
                            backStack.removeLastOrNull()
                            backStack.add(UserPickerNavKey)
                        }
                    }
                )
            }

            entry<AboutScreenNavKey> {
                AboutScreen(
                    onNavigateBack = {
                        scope.launch {
                            backStack.removeLastOrNull()
                            backStack.add(MainScreenNavKey)
                        }
                    }
                )
            }

            entry<SettingsScreenNavKey> {
                SettingsScreen(
                    onMenuOpen = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            }

            entry<UserPickerNavKey> {
                UserPickerScreen(
                    onNavigateBack = {
                        scope.launch {
                            backStack.removeLastOrNull()
                            backStack.add(MainScreenNavKey)
                        }
                    }
                )
            }
        }
    )
}
