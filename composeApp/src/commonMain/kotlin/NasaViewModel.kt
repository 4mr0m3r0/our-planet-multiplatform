import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

data class NasaImageUiState(val images: List<Item>)

class NasaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NasaImageUiState(emptyList()))
    val uiState = _uiState.asStateFlow()
    private val httpClient = HttpClient() {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }
    
    fun updateImages() {
        viewModelScope.launch {
            val nasaImages = getNasaImages()
            _uiState.update {
                it.copy(images = nasaImages.collection.items)
            }
        }
    }

    override fun onCleared() {
        httpClient.close()
    }
    
    private suspend fun getNasaImages(): NasaImages =
        httpClient
            .get("https://images-api.nasa.gov/search?q=planet%20earth")
            .body<NasaImages>()
} 