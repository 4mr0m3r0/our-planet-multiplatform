package universe.presentation

import Item
import UniverseViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun UniverseScreen(viewModel: UniverseViewModel, paddingValues: PaddingValues) {
    val uiState by viewModel.uiState.collectAsState()
    UniverseContent(uiState = uiState, paddingValues = paddingValues)
    LaunchedEffect(viewModel) {
        viewModel.updateImages()
    }
}

@Composable
private fun UniverseContent(uiState: UniverseUiState, paddingValues: PaddingValues) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 5.dp,
                top = paddingValues.calculateTopPadding(),
                end = 5.dp,
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        items(uiState.images) { image ->
            NasaImageCell(item = image)
        }
    }
}

@Composable
private fun NasaImageCell(item: Item) {
    KamelImage(
        resource = asyncPainterResource(item.links.first().href),
        contentDescription = item.data.first().title,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth().aspectRatio(1.0f)
    )
}