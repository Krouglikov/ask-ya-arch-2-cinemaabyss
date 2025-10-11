package ask.example.yandex.architecture.sprint2.gateway

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.concurrent.ThreadLocalRandom

@RestController
class ProxyController(
    private val restTemplate: RestTemplate,
    private val env: Environment
) {
    private val gradualMigration = env.getProperty("gradual.migration", Boolean::class.java, false)

    @GetMapping("/api/movies")
    fun getMovies(): String {
        val migrationPercent = env.getProperty("movies.migration.percent", Int::class.java, 0)
        return if (!gradualMigration || ThreadLocalRandom.current().nextInt(100) > migrationPercent) {
            // Route to monolith
            restTemplate.getForObject(
                env.getProperty("monolith.url")?.plus("/api/movies") ?: "",
                String::class.java
            ) ?: "Monolith unavailable"
        } else {
            // Route to new movies service
            restTemplate.getForObject(
                env.getProperty("movies.service.url")?.plus("/api/movies") ?: "",
                String::class.java
            ) ?: "New service unavailable"
        }
    }

    @GetMapping("/api/users")
    fun getUsers(): String {
        val migrationPercent = env.getProperty("users.migration.percent", Int::class.java, 0)
        return if (!gradualMigration || ThreadLocalRandom.current().nextInt(100) > migrationPercent) {
            // Route to monolith
            restTemplate.getForObject(
                env.getProperty("monolith.url")?.plus("/api/users") ?: "",
                String::class.java
            ) ?: "Monolith unavailable"
        } else {
            // Route to new users service
            restTemplate.getForObject(
                env.getProperty("users.service.url")?.plus("/api/users") ?: "",
                String::class.java
            ) ?: "New service unavailable"
        }
    }

    @GetMapping("/health")
    fun healthCheck(): Map<String, Any> {
        return mapOf("status" to true)
    }
}