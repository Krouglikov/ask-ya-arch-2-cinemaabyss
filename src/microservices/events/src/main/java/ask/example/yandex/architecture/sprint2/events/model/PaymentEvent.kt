package ask.example.yandex.architecture.sprint2.events.model

data class PaymentEvent(
    val paymentId: Long,
    val userId: Long,
    val amount: Double,
    val status: String,
    val methodType: String,
    val timestamp: String
)