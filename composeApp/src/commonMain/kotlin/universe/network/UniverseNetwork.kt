package universe

import NasaImages
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class UniverseNetwork {
    private val httpClient = HttpClient() {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }
    
    suspend fun getNasaImages(): NasaImages =
        httpClient
            .get("https://images-api.nasa.gov/search?q=planet%20earth")
            .body<NasaImages>()
    
    fun close() {
        httpClient.close()
    }
}