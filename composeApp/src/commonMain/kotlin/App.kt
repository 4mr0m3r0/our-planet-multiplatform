import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CropLandscape
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material3.*
import androidx.compose.runtime.*
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.ui.tooling.preview.Preview
import universe.UniverseNetwork
import universe.presentation.UniverseScreen

@Composable
@Preview
fun App() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Mineral", "Animal", "Plant", "Universe")
    MaterialTheme {
        Surface {
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Forest, contentDescription = item) },
                                label = { Text(item) },
                                selected = selectedItem == index,
                                onClick = { selectedItem = index }
                            )
                        }
                    }
                },
                content = {

                }
            )
        }
    }
}
