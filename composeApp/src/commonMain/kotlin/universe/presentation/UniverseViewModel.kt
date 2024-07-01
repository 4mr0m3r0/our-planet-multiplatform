import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import universe.UniverseNetwork
import universe.presentation.UniverseUiState

class UniverseViewModel(private val network: UniverseNetwork) : ViewModel() {
    private val _uiState = MutableStateFlow(UniverseUiState(emptyList()))
    val uiState = _uiState.asStateFlow()
    
    fun updateImages() {
        viewModelScope.launch {
            val nasaImages = network.getNasaImages()
            _uiState.update {
                it.copy(images = nasaImages.collection.items)
            }
        }
    }

    override fun onCleared() {
        network.close()
    }
} 