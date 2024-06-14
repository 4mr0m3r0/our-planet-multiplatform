import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import features.NasaImageContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = getViewModel(Unit, viewModelFactory { NasaViewModel() })
        val uiState by viewModel.uiState.collectAsState()
        LaunchedEffect(viewModel) {
            viewModel.updateImages()
        }
        NasaImageContent(uiState)
    }
}

