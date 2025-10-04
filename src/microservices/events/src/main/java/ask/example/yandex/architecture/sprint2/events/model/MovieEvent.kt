package ask.example.yandex.architecture.sprint2.events.model

data class MovieEvent(
    val movieId: Long,
    val title: String,
    val action: String,
    val userId: Long
)