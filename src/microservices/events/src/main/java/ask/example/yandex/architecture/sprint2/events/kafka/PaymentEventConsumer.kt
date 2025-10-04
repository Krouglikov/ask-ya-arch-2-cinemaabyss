package ask.example.yandex.architecture.sprint2.events.kafka

import ask.example.yandex.architecture.sprint2.events.model.PaymentEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class PaymentEventConsumer {

    private val log = org.slf4j.LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["payment-events"])
    fun listenPaymentEvent(event: PaymentEvent) {
        log.info("Received PaymentEvent: $event")
    }
}