package ask.example.yandex.architecture.sprint2.events.kafka

import ask.example.yandex.architecture.sprint2.events.model.MovieEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MovieEventConsumer {

    private val log = org.slf4j.LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["movie-events"])
    fun listenMovieEvent(event: MovieEvent) {
        log.info("Received MovieEvent: $event")
    }
}