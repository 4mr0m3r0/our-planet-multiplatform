package navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import universe.presentation.UniverseScreen

@Composable
fun AppNavHost(
    startDestination: String = Routes.Mineral.path,
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues = PaddingValues()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = Routes.Mineral.path) { Text("Mineral Screen")  }
        composable(route = Routes.Plant.path) { Text("Plant Screen") }
        composable(route = Routes.Animal.path) { Text("Animal Screen") }
        composable(route = Routes.Universe.path) {
            UniverseScreen(paddingValues = paddingValues)
        }
    }
}