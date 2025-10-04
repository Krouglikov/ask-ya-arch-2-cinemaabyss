package ask.example.yandex.architecture.sprint2.events.kafka

import ask.example.yandex.architecture.sprint2.events.model.UserEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class UserEventConsumer {

    private val log = org.slf4j.LoggerFactory.getLogger(UserEventConsumer::class.java)

    @KafkaListener(topics = ["user-events"])
    fun listenUserEvent(event: UserEvent) {
        log.info("Received UserEvent: $event")
    }
}