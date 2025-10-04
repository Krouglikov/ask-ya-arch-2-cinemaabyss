package ask.example.yandex.architecture.sprint2.events.api

import ask.example.yandex.architecture.sprint2.events.model.MovieEvent
import ask.example.yandex.architecture.sprint2.events.model.PaymentEvent
import ask.example.yandex.architecture.sprint2.events.model.UserEvent
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*
import org.slf4j.LoggerFactory

@RestController
@RequestMapping("/api/events")
class EventController(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private val log = LoggerFactory.getLogger(EventController::class.java)

    @GetMapping("/health")
    fun healthCheck(): Map<String, Any> {
        return mapOf("status" to true)
    }

    // Эндпоинт для события пользователя
    @PostMapping("/user")
    fun createUserEvent(@RequestBody request: UserEventRequest): ResponseEntity<Map<String, String>> {
        log.info("Received user event: $request")
        val userEvent = UserEvent(
            userId = request.userId,
            username = request.username,
            action = request.action,
            timestamp = request.timestamp
        )
        kafkaTemplate.send("user-events", userEvent)
        log.info("User event sent")
        return ResponseEntity.status(HttpStatus.CREATED).body(mapOf("status" to "success"))
    }

    // Эндпоинт для события платежа
    @PostMapping("/payment")
    fun createPaymentEvent(@RequestBody request: PaymentEventRequest): ResponseEntity<Map<String, String>> {
        log.info("Received payment event: $request")
        val paymentEvent = PaymentEvent(
            paymentId = request.paymentId,
            userId = request.userId,
            amount = request.amount,
            status = request.status,
            methodType = request.methodType,
            timestamp = request.timestamp
        )
        kafkaTemplate.send("payment-events", paymentEvent)
        log.info("Payment event sent")
        return ResponseEntity.status(HttpStatus.CREATED).body(mapOf("status" to "success"))
    }

    // Эндпоинт для события фильма
    @PostMapping("/movie")
    fun createMovieEvent(@RequestBody request: MovieEventRequest): ResponseEntity<Map<String, String>> {
        log.info("Received movie event: $request")
        val movieEvent = MovieEvent(
            movieId = request.movieId,
            title = request.title,
            action = request.action,
            userId = request.userId
        )
        kafkaTemplate.send("movie-events", movieEvent)
        log.info("Movie event sent")
        return ResponseEntity.status(HttpStatus.CREATED).body(mapOf("status" to "success"))
    }
}