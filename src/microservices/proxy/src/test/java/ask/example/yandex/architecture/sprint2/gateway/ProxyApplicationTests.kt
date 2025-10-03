package ask.example.yandex.architecture.sprint2.gateway

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ProxyApplicationTests(@Autowired val mockMvc: MockMvc) {

    @Test
    @DisplayName("should return 200 for /health")
    fun `should return 200 for health`() {
        mockMvc.perform(get("/health"))
            .andExpect(status().isOk)
    }
}