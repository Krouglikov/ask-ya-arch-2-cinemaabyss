package ask.example.yandex.architecture.sprint2.events.model

data class UserEvent(
    val userId: Long,
    val username: String,
    val action: String,
    val timestamp: String
)