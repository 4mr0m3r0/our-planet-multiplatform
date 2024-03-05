import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class NasaImages(
    val collection: Collection
)

@Serializable
data class Collection(
    val items: List<Item> 
)

@Serializable
data class Item(
    val data: List<Data>,
    val links: List<Link>
)

@Serializable
data class Data(
    val title: String,
    @SerialName("media_type") val mediaType: String,
    @SerialName("date_created") val dateCreated: String,
    val description: String
)

@Serializable
data class Link(
    val href: String
)