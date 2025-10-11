package ask.example.yandex.architecture.sprint2.events.api
import com.fasterxml.jackson.annotation.JsonProperty

data class UserEventRequest(
    @JsonProperty("user_id") val userId: Long,
    val username: String,
    val action: String,
    val timestamp: String
)

data class PaymentEventRequest(
    @JsonProperty("payment_id") val paymentId: Long,
    @JsonProperty("user_id") val userId: Long,
    val amount: Double,
    val status: String,
    val timestamp: String,
    @JsonProperty("method_type") val methodType: String
)

data class MovieEventRequest(
    @JsonProperty("movie_id") val movieId: Long,
    val title: String,
    val action: String,
    @JsonProperty("user_id") val userId: Long
)




